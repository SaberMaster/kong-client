package com.i2bgod.kong.model.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.i2bgod.kong.model.admin.base.annotation.KongFK;
import com.i2bgod.kong.util.PluginUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
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

    private PluginUtils pluginUtils;

    private static Gson defaultGson = new Gson();

    public DblessJsonSerializer(PluginUtils pluginUtils) {
        this.pluginUtils = pluginUtils;
    }

    private Object getJsonConverter(Class<?> clz) {
        if (jsonConverterMap.containsKey(clz)) {
           return jsonConverterMap.get(clz);
        }
        return jsonConverterMap.computeIfAbsent(clz, newClz -> {
            try {
                if (clz.isAssignableFrom(PluginJsonTypeAdapter.class)) {
                  return new PluginJsonTypeAdapter<>(pluginUtils);
                }
                return newClz.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
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
            if (null != readMethod) {
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
            Object fieldObj = getFieldValue(src, field.getName());
            if (!field.isAnnotationPresent(JsonAdapter.class)) {
                jsonObject.add(getSerializedName(field), context.serialize(fieldObj, field.getType()));
            } else {
                Class<?> jsonAdaptorClz = field.getAnnotation(JsonAdapter.class).value();
                GsonBuilder gsonBuilder = new GsonBuilder();
                Object jsonConverter = getJsonConverter(jsonAdaptorClz);
                gsonBuilder.registerTypeAdapter(field.getType(), jsonConverter);
                Gson gson = gsonBuilder.create();
                JsonElement jsonElement = gson.toJsonTree(fieldObj);
                jsonObject.add(getSerializedName(field), jsonElement);
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
            jsonObject.add(getSerializedName(field), defaultGson.toJsonTree(o));
        });
        return jsonObject;
    }
}
