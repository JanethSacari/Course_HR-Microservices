package com.repliforce.hrworker.repositories;

import com.repliforce.hrworker.entities.Worker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class WorkerRepositoryTest {
    @Autowired
    private WorkerRepository workerRepository;

    private Worker worker;

    @BeforeEach
    void setUp() {
        workerRepository.deleteAll();
        worker = new Worker();
        worker.setName("Iris");
        worker.setDailyIncome(50.0);
        worker = workerRepository.save(worker);
    }

    @AfterEach
    void tearDown() {
        workerRepository.deleteAll();
    }

    @Test
    void saveShouldAssignId() {
        assertNotNull(worker.getId(), "Saved worker should have an id");
        assertTrue(worker.getId() > 0, "Saved worker id should be positive");
    }

    @Test
    void findAllShouldReturnSavedWorker() {
        List<Worker> all = workerRepository.findAll();
        assertEquals(1, all.size(), "There should be exactly one worker");
        assertEquals("Iris", all.get(0).getName(), "Worker name should match");
    }

    @Test
    void findByIdShouldReturnWorker() {
        Optional<Worker> found = workerRepository.findById(worker.getId());
        assertTrue(found.isPresent(), "Worker must be found by id");
        assertEquals(worker.getName(), found.get().getName(), "Found worker name should match");
    }

    @Test
    void deleteShouldRemoveWorker() {
        workerRepository.deleteById(worker.getId());
        assertTrue(workerRepository.findAll().isEmpty(), "Repository should be empty after delete");
    }
}