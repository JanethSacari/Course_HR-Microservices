package com.repliforce.hruser.config;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class AppConfigTest {
    private AppConfig appConfig;
    private BCryptPasswordEncoder encoder;

    @BeforeEach
    void setUp() {
        appConfig = new AppConfig();
    }

    @AfterEach
    void tearDown() {
        appConfig = null;
        encoder = null;
    }

    @Test
    void passwordEncoder() {
        encoder = appConfig.passwordEncoder();
        assertNotNull(encoder, "passwordEncoder should not be null");
        assertEquals(BCryptPasswordEncoder.class, encoder.getClass(), "should be BCryptPasswordEncoder");

        String raw = "secret";
        String hashed = encoder.encode(raw);

        assertNotEquals(raw, hashed, "encoded value should differ from raw");
        assertTrue(encoder.matches(raw, hashed), "encoder should match raw with hashed value");
    }
}