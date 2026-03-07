package com.repliforce.hr_oauth.entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;
    private Role hunterRole;
    private Role navigatorRole;

    @BeforeEach
    void setUp() {
        user = new User(1L, "Iris", "iris@repliforce.com", "iris123");
        hunterRole = new Role(1L, "ROLE_HUNTER");
        navigatorRole = new Role(2L, "ROLE_NAVIGATOR");
    }

    @AfterEach
    void tearDown() {
        user = null;
        hunterRole = null;
        navigatorRole = null;
    }

    @Test
    void getId() {
        assertEquals(1L, user.getId());
        assertNotNull(user.getId());
    }

    @Test
    void setId() {
        user.setId(5L);
        assertEquals(5L, user.getId());
    }

    @Test
    void getName() {
        assertEquals("Iris", user.getName());
        assertNotNull(user.getName());
    }

    @Test
    void setName() {
        user.setName("Alia");
        assertEquals("Alia", user.getName());
    }

    @Test
    void getEmail() {
        assertEquals("iris@repliforce.com", user.getEmail());
        assertNotNull(user.getEmail());
    }

    @Test
    void setEmail() {
        user.setEmail("alia@mavhunter.com");
        assertEquals("alia@mavhunter.com", user.getEmail());
    }

    @Test
    void getPassword() {
        assertEquals("iris123", user.getPassword());
        assertNotNull(user.getPassword());
    }

    @Test
    void setPassword() {
        user.setPassword("alia456");
        assertEquals("alia456", user.getPassword());
    }

    @Test
    void getRoles() {
        assertTrue(user.getRoles().isEmpty());

        user.getRoles().add(hunterRole);
        user.getRoles().add(navigatorRole);


        assertEquals(2, user.getRoles().size());
        assertTrue(user.getRoles().contains(hunterRole));
        assertTrue(user.getRoles().contains(navigatorRole));
    }
}