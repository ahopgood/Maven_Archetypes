package com.labs.reclusive.spring.boot.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labs.reclusive.spring.boot.service.Version;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MyControllerTest {

    private final HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter(new ObjectMapper());

    private Version versionService = mock(Version.class);
    private MyController controller = new MyController(versionService);

    @Test
    void testGetVersions() throws Exception {
        MockMvc mock = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(messageConverter)
                .build();
        mock.perform(MockMvcRequestBuilders.get("/versions"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetVersions_thenContentTypeIsJson() throws Exception {
        MockMvc mock = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(messageConverter)
                .build();
        mock.perform(MockMvcRequestBuilders.get("/versions"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }


    @Test
    void testGetVersions_thenResponseIsValueFromService() throws Exception {
        when(versionService.getVersion()).thenReturn("4");
        MockMvc mock = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(messageConverter)
                .build();
        mock.perform(MockMvcRequestBuilders.get("/versions"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json("{\"version\":\"4\"}"));
    }

    //Assert content-type is Json
    //Assert that mocked property is returned
    //Assert /versions GET exists
    //Assert other verbs do not work
}