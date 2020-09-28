package com.i2bgod.kong.model.adapter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Collection;

/**
 * @author: Lyn
 * @date: 05/08/2020
 */
public class EmptyArrayJsonDeserializer<T extends Collection<R>, R> extends JsonDeserializer<T> implements ContextualDeserializer {

    private JavaType valueType;
    @Override
    @SuppressWarnings("unchecked")
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        //Find here the targetClass to be deserialized
        valueType = ctxt.getContextualType();
        return this;
    }

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String text = p.getText();
        String trimVal = StringUtils.trim(text);
        if (StringUtils.isBlank(trimVal) || '[' != trimVal.charAt(0)) {
            return null;
        }
        return p.getCodec().readValue(p, valueType);
    }
}
