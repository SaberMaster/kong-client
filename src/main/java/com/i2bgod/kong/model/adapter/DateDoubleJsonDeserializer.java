package com.i2bgod.kong.model.adapter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Date;

/**
 * @author: Lyn
 * @date: 05/08/2020
 */
public class DateDoubleJsonDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return new Date(new Double(jsonParser.getDoubleValue()).longValue());
    }
}
