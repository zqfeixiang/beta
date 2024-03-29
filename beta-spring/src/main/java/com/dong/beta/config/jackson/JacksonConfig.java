package com.dong.beta.config.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {
    @Bean
    @Primary
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder){
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.setSerializerFactory(objectMapper.getSerializerFactory().withSerializerModifier(
                new CustomizeBeanSerializerModifier()
        ));
        return objectMapper;
    }
}
