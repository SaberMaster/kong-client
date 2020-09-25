package com.i2bgod.kong.model.adapter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.i2bgod.kong.model.admin.base.annotation.KongFK;
import lombok.extern.slf4j.Slf4j;
import okio.Buffer;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * for compress entity to db_entity
 * for example: route.service.id = 1 -> route.service = 1
 * @author: Lyn
 * @date: 05/08/2020
 */
@Slf4j
public class DblessJsonSerializer<T> implements JsonSerializer<T> {
    private Map<Class<?>, Object> jsonConverterMap = new ConcurrentHashMap<>(4);

    private Object getJsonConverter(Class<?> clz) {
        if (jsonConverterMap.containsKey(clz)) {
           return jsonConverterMap.get(clz);
        }
        return jsonConverterMap.computeIfAbsent(clz, newClz -> {
            try {
                return newClz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private String getSerializedName(Field field) {
        SerializedName annotation = field.getAnnotation(SerializedName.class);
        String value;
        if (null == annotation || StringUtils.isBlank(annotation.value())) {
            log.debug("SerializeName annotation is missing on {}", field);
            value = field.getName();
        } else {
           value = annotation.value();
        }
        return value;
    }

    private Object getFieldValue(Object target, String fieldName) {
        PropertyDescriptor propertyDescriptor = null;
        try {
            propertyDescriptor = new PropertyDescriptor(fieldName, target.getClass());
            Method readMethod = propertyDescriptor.getReadMethod();
            if (null == readMethod) {
               return readMethod.invoke(target);
            } else {
               return FieldUtils.readField(target, fieldName, true);
            }
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        List<Field> allFieldsList = FieldUtils.getAllFieldsList(src.getClass());
        List<Field> fkFields = FieldUtils.getFieldsListWithAnnotation(src.getClass(), KongFK.class);
        allFieldsList.removeAll(fkFields);
        allFieldsList.forEach(field -> {
            try {
                Object fieldObj = getFieldValue(src, field.getName());
                if (!field.isAnnotationPresent(JsonAdapter.class)) {
                    jsonObject.add(getSerializedName(field), context.serialize(fieldObj, field.getType()));
                } else {
                    Class<?> jsonAdaptorClz = field.getAnnotation(JsonAdapter.class).value();
                    //typeAdapter
                    if (TypeAdapter.class.isAssignableFrom(jsonAdaptorClz)) {
                        // just for get render result of gson typeAdapter
                        Buffer buffer = new Buffer();
                        Writer writer = new OutputStreamWriter(buffer.outputStream(), StandardCharsets.UTF_8);
                        Gson gson = new Gson();
                        JsonWriter jsonWriter = gson.newJsonWriter(writer);
                        TypeAdapter typeAdapter = (TypeAdapter) getJsonConverter(jsonAdaptorClz);
                        typeAdapter.write(jsonWriter, fieldObj);
                        jsonWriter.close();
                        String bufStr = buffer.readUtf8();
                        buffer.writeUtf8(bufStr);
                        JsonReader jsonReader = gson.newJsonReader(new InputStreamReader(buffer.inputStream()));
                        // simple check result is string/number
                        if (bufStr.contains("\"")) {
                            TypeAdapter<String> adapter = gson.getAdapter(String.class);
                            String val = adapter.read(jsonReader);
                            jsonObject.addProperty(getSerializedName(field), val);
                        } else  {
                            TypeAdapter<Number> adapter = gson.getAdapter(Number.class);
                            Number val = adapter.read(jsonReader);
                            jsonObject.addProperty(getSerializedName(field), val);
                        }
                    // jsonSerializer
                    } else if (JsonSerializer.class.isAssignableFrom(jsonAdaptorClz)) {
                        JsonSerializer serializer = (JsonSerializer) getJsonConverter(jsonAdaptorClz);
                        jsonObject.add(getSerializedName(field), serializer.serialize(fieldObj, field.getType(), context));
                    // other case
                    } else {
                        jsonObject.add(getSerializedName(field), context.serialize(fieldObj));
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        if (CollectionUtils.isEmpty(fkFields)) {
            return jsonObject;
        }

        // extract fk field
        fkFields.forEach(field -> {
            Object target = getFieldValue(src, field.getName());
            if (null == target) {
                return;
            }
            Object o = getFieldValue(target, field.getAnnotation(KongFK.class).name());
            if (o instanceof Number) {
                jsonObject.addProperty(getSerializedName(field), ((Number) o));
            } else if (o instanceof String) {
                jsonObject.addProperty(getSerializedName(field), ((String) o));
            } else {
                log.warn("not support fk type for target:{}, on field:{}", target, field);
                throw new RuntimeException("not support fk type");
            }
        });
        return jsonObject;
    }
}
