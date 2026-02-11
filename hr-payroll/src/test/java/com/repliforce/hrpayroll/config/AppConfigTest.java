package com.repliforce.hrpayroll.config;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class AppConfigTest {

    private AnnotationConfigApplicationContext context;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @AfterEach
    void tearDown() {
        if (context != null) {
            context.close();
        }
    }

    @Test
    void registerRestTemplate() {
        assertTrue(context.containsBean("registerRestTemplate"), "Bean name should exist");
        Object bean = context.getBean("registerRestTemplate");
        assertNotNull(bean, "Bean should not be null");
    }

    @Test
    void restTemplateIsSingleton() {
        RestTemplate a = context.getBean(RestTemplate.class);
        RestTemplate b = context.getBean(RestTemplate.class);
        assertSame(a, b, "RestTemplate bean should be singleton by default");
    }
}