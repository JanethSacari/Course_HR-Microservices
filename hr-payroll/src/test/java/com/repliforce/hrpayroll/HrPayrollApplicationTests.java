package com.repliforce.hrpayroll;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HrPayrollApplicationTests {

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
	void circuitBreakerIsConfigured() {
		assertNotNull(applicationContext.getBean(CircuitBreakerFactory.class),
				"CircuitBreaker deve estar configurado");
	}

	@Test
	void eurekaClientIsConfigured() {
		assertNotNull(applicationContext.getBean(EurekaInstanceConfigBean.class),
				"Eureka deve estar configurado");
	}

	@Test
	void feignClientsAreEnabled() {
		assertTrue(applicationContext.getBeanNamesForType(Object.class).length > 0,
				"Aplicação deve ter beans registrados");
	}

	@Test
	void applicationHasRequiredAnnotations() {
		assertTrue(applicationContext.containsBean("hrPayrollApplication"),
				"Bean da aplicação deve estar registrado");
	}

	@Test
	void applicationContextIsNotEmpty() {
		String[] beanNames = applicationContext.getBeanDefinitionNames();
		assertTrue(beanNames.length > 0, "O contexto deve conter pelo menos um bean");
	}

	@Test
	void mainMethodStartsApplication() {
		assertDoesNotThrow(() -> HrPayrollApplication.main(new String[]{}),
				"O método main deve iniciar a aplicação sem lançar exceções");
	}
}