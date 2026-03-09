package com.repliforce.hrpayroll.entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PaymentTest {

    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment("Sigma", 100.0, 5);
    }

    @AfterEach
    void tearDown() {
        payment = null;
    }

    @Test
    void testEmptyConstructor() {
        Payment emptyPayment = new Payment();
        assertNull(emptyPayment.getName());
        assertNull(emptyPayment.getDailyIncome());
        assertNull(emptyPayment.getDays());
    }

    @Test
    void testConstructorWithParameters() {
        Payment paymentWithParams = new Payment("Vile", 200.0, 10);
        assertEquals("Vile", paymentWithParams.getName());
        assertEquals(200.0, paymentWithParams.getDailyIncome());
        assertEquals(10, paymentWithParams.getDays());
    }

    @Test
    void getName() {
        assertEquals("Sigma", payment.getName());
    }

    @Test
    void setName() {
        payment.setName("Dynamo");
        assertEquals("Dynamo", payment.getName());
    }

    @Test
    void getDailyIncome() {
        assertEquals(100.0, payment.getDailyIncome());
    }

    @Test
    void setDailyIncome() {
        payment.setDailyIncome(150.5);
        assertEquals(150.5, payment.getDailyIncome());
    }

    @Test
    void getDays() {
        assertEquals(5, payment.getDays());
    }

    @Test
    void setDays() {
        payment.setDays(10);
        assertEquals(10, payment.getDays());
    }

    @Test
    void getTotal() {
        assertEquals(500.0, payment.getTotal(), 1e-9);
    }
}