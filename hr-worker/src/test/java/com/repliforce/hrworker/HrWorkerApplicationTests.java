package com.repliforce.hrworker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.NONE,
		properties = {
				"eureka.client.enabled=false",
				"spring.cloud.discovery.enabled=false",
				"test.config=default"
		}
)
class HrWorkerApplicationTests {
	private final String[] KEYS = {
			"eureka.client.enabled",
			"spring.cloud.discovery.enabled",
			"test.config"
	};
	private final Map<String, String> previous = new HashMap<>();

	@BeforeEach
	void setUp() {
		for (String k : KEYS) {
			previous.put(k, System.getProperty(k));
		}
		System.setProperty("eureka.client.enabled", "false");
		System.setProperty("spring.cloud.discovery.enabled", "false");
		System.setProperty("test.config", "default");
	}

	@AfterEach
	void tearDown() {
		for (String k : KEYS) {
			String val = previous.get(k);
			if (val == null) {
				System.clearProperty(k);
			} else {
				System.setProperty(k, val);
			}
		}
		previous.clear();
	}


	@Test
	void contextLoads() {
	}

	@Test
	void mainMethodRunsWithoutThrowing() {
		assertDoesNotThrow(() -> HrWorkerApplication.main(new String[]{}));
	}

	@Test
	void classHasExpectedAnnotations() {
		assertNotNull(HrWorkerApplication.class.getAnnotation(SpringBootApplication.class),
				"SpringBootApplication annotation should be present");
		assertNotNull(HrWorkerApplication.class.getAnnotation(EnableEurekaClient.class),
				"EnableEurekaClient annotation should be present");
	}
}