package com.repliforce.hrapigatewayzuul;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HrApiGatewayZuulApplicationTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        assertNotNull(applicationContext, "O contexto da aplicação não deve ser nulo");
        assertNotNull(applicationContext.getBean(CircuitBreakerFactory.class), "CircuitBreaker deve estar configurado");
        assertNotNull(applicationContext.getBean(EurekaInstanceConfigBean.class), "Eureka deve estar configurado");
        assertTrue(applicationContext.getBeanNamesForType(Object.class).length > 0, "Aplicação deve ter beans registrados");
    }

    @Test
    void mainMethodStartsApplication() {
        assertDoesNotThrow(() -> HrApiGatewayZuulApplication.main(new String[]{}),
                "O método main deve iniciar a aplicação sem lançar exceções");
    }

    @Test
    void eurekaClientIsConfigured() {
        assertNotNull(applicationContext.getBean(EurekaInstanceConfigBean.class),
                "Eureka deve estar configurado");
    }
}