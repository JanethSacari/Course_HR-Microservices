package com.repliforce.hreurekaserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class HrEurekaServerApplicationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void mainMethodStartsApplication() {
        assertDoesNotThrow(() -> HrEurekaServerApplication.main(new String[]{}),
                "O método main deve iniciar a aplicação sem lançar exceções");
    }
}