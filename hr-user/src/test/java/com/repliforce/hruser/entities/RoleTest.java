package com.repliforce.hruser.entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {
    private Role role;

    @BeforeEach
    void setUp() {
        role = new Role(1L, "Commander");
    }

    @AfterEach
    void tearDown() {
        role = null;
    }

    @Test
    void getId() {
        assertEquals(1L, role.getId());
    }

    @Test
    void setId() {
        role.setId(42L);
        assertEquals(42L, role.getId());
    }

    @Test
    void getRoleName() {
        assertEquals("Commander", role.getRoleName());
    }

    @Test
    void setRoleName() {
        role.setRoleName("Hunter");
        assertEquals("Hunter", role.getRoleName());
    }

    @Test
    void testEquals() {
        Role a = new Role(1L, "Commander");
        Role b = new Role(2L, "Commander");
        Role c = new Role(3L, "Hunter");

        assertTrue(a.equals(a));
        assertTrue(a.equals(b) && b.equals(a));
        assertFalse(a.equals(c));
        assertFalse(a.equals(null));
        assertFalse(a.equals(new Object()));

        Role r1 = new Role(10L, null);
        Role r2 = new Role(11L, null);
        assertTrue(r1.equals(r2));
    }

    @Test
    void testHashCode() {
        Role a = new Role(1L, "Navigator");
        Role b = new Role(2L, "Navigator");
        Role c = new Role(3L, "General");

        assertEquals(a.hashCode(), b.hashCode());

        assertNotEquals(a.hashCode(), c.hashCode());

        Role r1 = new Role(10L, null);
        Role r2 = new Role(11L, null);
        assertEquals(r1.hashCode(), r2.hashCode());
    }
}