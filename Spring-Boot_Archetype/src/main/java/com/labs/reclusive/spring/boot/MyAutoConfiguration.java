package com.labs.reclusive.spring.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyAutoConfiguration {

    @Bean
    public MyProperties myProperties(
        @Value("${properties.name}") String name,
        @Value("${properties.version}") Long version
    ){
        MyProperties properties = new MyProperties();
        properties.setName(name);
        properties.setVersion(version);
        return properties;
    }
}
