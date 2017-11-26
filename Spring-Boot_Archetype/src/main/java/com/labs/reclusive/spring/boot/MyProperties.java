package com.labs.reclusive.spring.boot;

public class MyProperties {

    private String name;
    private Long version;

    MyProperties(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
