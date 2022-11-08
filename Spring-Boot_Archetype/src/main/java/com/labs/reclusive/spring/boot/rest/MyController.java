package com.labs.reclusive.spring.boot.rest;

import com.labs.reclusive.spring.boot.rest.model.VersionResponse;
import com.labs.reclusive.spring.boot.service.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class MyController {

    private Version versionService;

    @Autowired
    public MyController(Version versionService) {
        this.versionService = versionService;
    }

    @GetMapping(value = "/versions", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<VersionResponse> getVersion() {
        return ResponseEntity
                .ok()
                .body(new VersionResponse(this.versionService.getVersion()));
    }
}
