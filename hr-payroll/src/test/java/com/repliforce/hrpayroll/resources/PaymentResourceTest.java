package com.repliforce.hrpayroll.resources;

import com.repliforce.hrpayroll.entities.Payment;
import com.repliforce.hrpayroll.services.PaymentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class PaymentResourceTest {

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentResource paymentResource;

    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment("X", 1000.0, 10);
    }

    @AfterEach
    void tearDown() {
        payment = null;
        reset(paymentService);
    }

    @Test
    void getPayment() {
        when(paymentService.getPayment(1L, 10)).thenReturn(payment);

        ResponseEntity<Payment> result = paymentResource.getPayment(1L, 10);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("X", result.getBody().getName());
        assertEquals(1000.0, result.getBody().getDailyIncome());
        assertEquals(10, result.getBody().getDays());
        verify(paymentService, times(1)).getPayment(1L, 10);
    }

    @Test
    void getPaymentAlternative() {
        ResponseEntity<Payment> result = paymentResource.getPaymentAlternative(1L, 10);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Red", result.getBody().getName());
        assertEquals(500.0, result.getBody().getDailyIncome());
        assertEquals(10, result.getBody().getDays());
    }
}