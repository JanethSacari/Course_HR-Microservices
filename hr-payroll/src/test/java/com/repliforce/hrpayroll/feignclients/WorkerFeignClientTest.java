package com.repliforce.hrpayroll.feignclients;

import com.repliforce.hrpayroll.entities.Worker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkerFeignClientTest {

    @Mock
    private WorkerFeignClient workerFeignClient;

    private Worker worker;

    @BeforeEach
    void setUp() {
        worker = new Worker();
        worker.setId(1L);
        worker.setName("X");
    }

    @AfterEach
    void tearDown() {
        worker = null;
    }

    @Test
    void findById() {
        ResponseEntity<Worker> response = new ResponseEntity<>(worker, HttpStatus.OK);
        when(workerFeignClient.findById(1L)).thenReturn(response);

        ResponseEntity<Worker> result = workerFeignClient.findById(1L);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1L, result.getBody().getId());
        assertEquals("X", result.getBody().getName());
        verify(workerFeignClient, times(1)).findById(1L);
    }

    @Test
    void findByIdNotFound() {
        when(workerFeignClient.findById(999L)).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        ResponseEntity<Worker> result = workerFeignClient.findById(999L);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(workerFeignClient, times(1)).findById(999L);
    }
}