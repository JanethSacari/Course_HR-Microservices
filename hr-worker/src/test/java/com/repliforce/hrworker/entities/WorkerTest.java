package com.repliforce.hrworker.entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorkerTest {

    private Worker worker;

    @BeforeEach
    void setUp() {
        worker = new Worker(1L, "X", 500.0);
    }

    @AfterEach
    void tearDown() {
        worker = null;
    }

    @Test
    void getId() {
        assertEquals(1L, worker.getId());
    }

    @Test
    void setId() {
        worker.setId(2L);
        assertEquals(2L, worker.getId());
    }

    @Test
    void getName() {
        assertEquals("X", worker.getName());
    }

    @Test
    void setName() {
        worker.setName("Zero");
        assertEquals("Zero", worker.getName());
    }

    @Test
    void getDailyIncome() {
        assertEquals(500.0, worker.getDailyIncome());
    }

    @Test
    void setDailyIncome() {
        worker.setDailyIncome(750.0);
        assertEquals(750.0, worker.getDailyIncome());
    }
}