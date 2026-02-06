package com.repliforce.hruser.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getId() {
        User user = new User(1L, "Iris", "iris@repliforce.com", "navigatorPass");
        assertEquals(1L, user.getId());
    }

    @Test
    void setId() {
        User user = new User();
        user.setId(42L);
        assertEquals(42L, user.getId());
    }

    @Test
    void getName() {
        User user = new User(1L, "X", "x@mavhunter.com", "hunterPass");
        assertEquals("X", user.getName());
    }

    @Test
    void setName() {
        User user = new User();
        user.setName("Zero");
        assertEquals("Zero", user.getName());
    }

    @Test
    void getEmail() {
        User user = new User(1L, "Axl", "axl@mavhunter.com", "hunter2Pass");
        assertEquals("axl@mavhunter.com", user.getEmail());
    }

    @Test
    void setEmail() {
        User user = new User();
        user.setEmail("colonel@repliforce.com");
        assertEquals("colonel@repliforce.com", user.getEmail());
    }

    @Test
    void getPassword() {
        User user = new User(1L, "Alia", "alia@mavhunter.com", "navigator2Pass");
        assertEquals("navigator2Pass", user.getPassword());
    }

    @Test
    void setPassword() {
        User user = new User();
        user.setPassword("newhunterpass");
        assertEquals("newhunterpass", user.getPassword());
    }

    @Test
    void getRoles() {
        User user = new User();
        assertNotNull(user.getRoles(), "roles set should be initialized");
        assertTrue(user.getRoles().isEmpty(), "roles set should be empty initially");

        Role role = new Role();
        user.getRoles().add(role);
        assertEquals(1, user.getRoles().size());
        assertTrue(user.getRoles().contains(role));
    }
}