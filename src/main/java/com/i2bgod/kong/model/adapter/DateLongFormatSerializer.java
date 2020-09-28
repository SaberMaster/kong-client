package com.i2bgod.kong.model.adapter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * @author: Lyn
 * @date: 05/08/2020
 */
public class DateLongFormatSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (null != date) {
            jsonGenerator.writeNumber(date.getTime() / 1000);
        } else {
            jsonGenerator.writeNull();
        }
    }
}
