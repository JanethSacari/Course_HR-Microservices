package com.repliforce.hr_oauth.entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    private Role role;
    private Role roleComparison;

    @BeforeEach
    void setUp() {
        role = new Role(1L, "ROLE_HUNTER");
        roleComparison = new Role(2L, "ROLE_HUNTER");
    }

    @AfterEach
    void tearDown() {
        role = null;
        roleComparison = null;
    }

    @Test
    void getId() {
        assertEquals(1L, role.getId());
        assertNotNull(role.getId());
    }

    @Test
    void setId() {
        role.setId(5L);
        assertEquals(5L, role.getId());
    }

    @Test
    void getRoleName() {
        assertEquals("ROLE_HUNTER", role.getRoleName());
        assertNotNull(role.getRoleName());
    }

    @Test
    void setRoleName() {
        role.setRoleName("ROLE_HUNTER");
        assertEquals("ROLE_HUNTER", role.getRoleName());
    }

    @Test
    void testEquals() {
        assertEquals(role, roleComparison);

        Role roleDifferent = new Role(1L, "ROLE_NAVIGATOR");

        assertNotEquals(role, roleDifferent);
        assertNotEquals(null, role);
        assertNotEquals(new Object(), role);
    }

    @Test
    void testHashCode() {
        assertEquals(role.hashCode(), roleComparison.hashCode());

        Role roleDifferent = new Role(1L, "ROLE_NAVIGATOR");
        assertNotEquals(role.hashCode(), roleDifferent.hashCode());
    }
}