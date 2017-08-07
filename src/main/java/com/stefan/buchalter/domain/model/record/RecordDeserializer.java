package com.stefan.buchalter.domain.model.record;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.stefan.buchalter.common.DateUtil;

import java.io.IOException;
import java.time.LocalDate;

// TODO move this class to web package
public class RecordDeserializer extends JsonDeserializer<Record> {

    @Override
    public Record deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String type = node.get("type").asText();
        String title = node.get("title").asText();
        Double pitValue = node.get("pitValue").doubleValue();


        LocalDate date = DateUtil.parse(node.get("date").asText());

        return new Record(title, date, pitValue);
    }
}
