package com.labs.reclusive.spring.boot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class VersionService implements Version {

    @Value("${properties.version}")
    private String version;


    @Override
    public String getVersion() {
        return version;
    }
}
