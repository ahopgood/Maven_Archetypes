package com.labs.reclusive.spring.boot.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VersionResponse {
    @JsonProperty
    private final String version;

    @JsonCreator
    public VersionResponse (String version) {
        this.version = version;
    }
}
