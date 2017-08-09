package com.stefan.buchalter.web.config;

import com.stefan.buchalter.domain.model.record.Record;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

//@Configuration
public class WebConfig {

//    @Bean
//    public Jackson2ObjectMapperBuilder jacksonBuilder() {
//        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
//        builder.deserializerByType(Record.class, new RecordDeserializer());
//        return builder;
//    }
}
