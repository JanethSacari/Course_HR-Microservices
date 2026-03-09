package com.repliforce.hrpayroll.services;

import com.repliforce.hrpayroll.entities.Payment;
import com.repliforce.hrpayroll.entities.Worker;
import com.repliforce.hrpayroll.feignclients.WorkerFeignClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private WorkerFeignClient workerFeignClient;

    @InjectMocks
    private PaymentService paymentService;

    private Worker worker;
    private Payment payment;

    @BeforeEach
    void setUp() {
        worker = new Worker();
        worker.setId(1L);
        worker.setName("X");
        worker.setDailyIncome(100.0);
    }

    @AfterEach
    void tearDown() {
        worker = null;
        payment = null;
        reset(workerFeignClient);
    }

    @Test
    void getPayment() {
        ResponseEntity<Worker> response = new ResponseEntity<>(worker, HttpStatus.OK);
        when(workerFeignClient.findById(1L)).thenReturn(response);

        payment = paymentService.getPayment(1L, 10);

        assertNotNull(payment);
        assertEquals("X", payment.getName());
        assertEquals(100.0, payment.getDailyIncome());
        assertEquals(10, payment.getDays());
        verify(workerFeignClient, times(1)).findById(1L);
    }
}