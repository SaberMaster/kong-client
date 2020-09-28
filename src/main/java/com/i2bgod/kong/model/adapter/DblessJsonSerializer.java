package com.i2bgod.kong.model.adapter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.i2bgod.kong.model.admin.base.annotation.KongFK;
import com.i2bgod.kong.util.PluginUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
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
public class DblessJsonSerializer<T> extends JsonSerializer<T> {
    private Map<Class<?>, Object> jsonConverterMap = new ConcurrentHashMap<>(4);

    private PluginUtils pluginUtils;

    public DblessJsonSerializer(PluginUtils pluginUtils) {
        this.pluginUtils = pluginUtils;
    }

    private Object getJsonConverter(Class<?> clz) {
        if (jsonConverterMap.containsKey(clz)) {
           return jsonConverterMap.get(clz);
        }
        return jsonConverterMap.computeIfAbsent(clz, newClz -> {
            try {
                if (clz.isAssignableFrom(PluginJsonDeserializer.class)) {
                  return new PluginJsonDeserializer<>(pluginUtils);
                }
                return newClz.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private String getJsonName(Field field) {
        JsonProperty annotation = field.getAnnotation(JsonProperty.class);
        String value;
        if (null == annotation || StringUtils.isBlank(annotation.value())) {
            log.debug("json name annotation is missing on {}", field);
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

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (null == value) {
            gen.writeNull();
            return;
        }

        Map<String, Object> objMap = new HashMap<>();
        List<Field> allFieldsList = FieldUtils.getAllFieldsList(value.getClass());
        List<Field> fkFields = FieldUtils.getFieldsListWithAnnotation(value.getClass(), KongFK.class);
        allFieldsList.removeAll(fkFields);
        allFieldsList.forEach(field -> {
            Object fieldObj = getFieldValue(value, field.getName());
            if (null == fieldObj) {
                return;
            }
            if (!field.isAnnotationPresent(JsonSerialize.class)) {
                objMap.put(getJsonName(field), fieldObj);
            } else {
                Class<? extends JsonSerializer> jsonSerializerClz = field.getAnnotation(JsonSerialize.class).using();
                SimpleModule simpleModule = new SimpleModule();
                simpleModule.addSerializer(field.getType(), (JsonSerializer) getJsonConverter(jsonSerializerClz));
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                objectMapper.registerModule(simpleModule);
                objMap.put(getJsonName(field), objectMapper.valueToTree(fieldObj));
            }
        });

        if (CollectionUtils.isEmpty(fkFields)) {
            gen.writeObject(objMap);
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // extract fk field
        fkFields.forEach(field -> {
            Object target = getFieldValue(value, field.getName());
            if (null == target) {
                return;
            }
            Object o = getFieldValue(target, field.getAnnotation(KongFK.class).name());
            if (null == o) {
                return;
            }
            objMap.put(getJsonName(field), objectMapper.valueToTree(o));
        });
        gen.writeObject(objMap);
    }
}
