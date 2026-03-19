package com.repliforce.hrconfigserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(properties = {
        "spring.cloud.config.server.git.username=test-user",
        "spring.cloud.config.server.git.uri=https://github.com/fake/repo"
})
class HrConfigServerApplicationTest {

    @Autowired
    private HrConfigServerApplication application;

    @Test
    void testUsernameInjected() {
        String username = (String) ReflectionTestUtils.getField(application, "username");
        assertNotNull(username);
    }

    @Test
    void runMethodExecutesWithoutException() {
        HrConfigServerApplication app = new HrConfigServerApplication();
        assertDoesNotThrow(() -> app.run(),
                "O método run deve ser executado sem lançar exceções");
    }

    @Test
    void mainMethodStartsApplication() {
        System.setProperty("GH_USER", "test-user");
        System.setProperty("GH_TOKEN", "test-token");

        System.setProperty("server.port", "0");
        System.setProperty("spring.cloud.config.server.git.uri", "https://github.com/mock/repo");

        assertDoesNotThrow(() -> HrConfigServerApplication.main(new String[]{}));
    }
}