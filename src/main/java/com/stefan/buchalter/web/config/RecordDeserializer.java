package com.stefan.buchalter.web.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.stefan.buchalter.common.DateUtil;
import com.stefan.buchalter.domain.model.record.Record;

import java.io.IOException;
import java.time.LocalDate;

public class RecordDeserializer extends JsonDeserializer<Record> {

    @Override
    public Record deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String type = node.get("type").asText();
        String title = node.get("title").asText();
        LocalDate date = DateUtil.parse(node.get("date").asText());

        if (Record.Type.PIT.name().equals(type)) {
            Double pitValue = node.get("pitValue").doubleValue();
            return new Record(title, date, pitValue);
        }

        if (Record.Type.VAT.name().equals(type)) {
            Double netValue = node.get("netValue").doubleValue();
            String vatRate = node.get("vatRate").asText();
            Double vatDeductionRate = node.get("vatDeductionRate").asDouble();
            return new Record(title, date, netValue, Record.VatRate.valueOf(vatRate), vatDeductionRate);
        }

        throw new IllegalArgumentException("Unknown record type: " + type);
    }
}
