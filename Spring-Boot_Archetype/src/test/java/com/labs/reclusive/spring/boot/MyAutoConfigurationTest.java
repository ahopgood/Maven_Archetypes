package com.labs.reclusive.spring.boot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class MyAutoConfigurationTest {

    @Autowired
    MyProperties props;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void myProperties() throws Exception {
        assertEquals("alex", props.getName());
        assertEquals((Long)2L, props.getVersion());
    }

}