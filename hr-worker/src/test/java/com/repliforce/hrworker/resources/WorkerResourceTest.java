package com.repliforce.hrworker.resources;

import com.repliforce.hrworker.entities.Worker;
import com.repliforce.hrworker.repositories.WorkerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkerResourceTest {
    @Mock
    private WorkerRepository workerRepository;

    @Mock
    private Environment environment;

    @InjectMocks
    private WorkerResource workerResource;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(workerResource, "testConfig", "test-value");
    }

    @AfterEach
    void tearDown() {
        reset(workerRepository, environment);
    }

    @Test
    void getConfigs() {
        ResponseEntity<Void> response = workerResource.getConfigs();
        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void findAll() {
        Worker mockWorker = mock(Worker.class);
        when(workerRepository.findAll()).thenReturn(List.of(mockWorker));

        ResponseEntity<List<Worker>> response = workerResource.findAll();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertSame(mockWorker, response.getBody().get(0));

        verify(workerRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        Long id = 1L;
        Worker mockWorker = mock(Worker.class);
        when(workerRepository.findById(id)).thenReturn(Optional.of(mockWorker));
        when(environment.getProperty("local.server.port")).thenReturn("8080");

        ResponseEntity<Worker> response = workerResource.findById(id);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertSame(mockWorker, response.getBody());

        verify(workerRepository, times(1)).findById(id);
        verify(environment, times(1)).getProperty("local.server.port");
    }
}