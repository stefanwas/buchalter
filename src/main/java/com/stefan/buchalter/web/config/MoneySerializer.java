package com.stefan.buchalter.web.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;

public class MoneySerializer  extends JsonSerializer<Double> {

    private static final DecimalFormat FORMATTER = new DecimalFormat("#.00");

    @Override
    public void serialize(Double value, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeString(FORMATTER.format(value));
    }
}
