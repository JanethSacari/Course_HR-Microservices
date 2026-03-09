package com.repliforce.hr_oauth.entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

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

    @Test
    void testConstructorEmpty() {
        User hunterB = new User();
        assertNotNull(hunterB);
        assertNull(hunterB.getId());
        assertNull(hunterB.getName());
        assertNull(hunterB.getEmail());
        assertNull(hunterB.getPassword());
        assertTrue(hunterB.getRoles().isEmpty());
    }

    @Test
    void testConstructorCustom() {
        User customUser = new User(
                2L,
                "Colonel",
                "colonel@repliforce.com",
                "colonel123");
        assertNotNull(customUser);
        assertEquals(2L, customUser.getId());
        assertEquals("Colonel", customUser.getName());
        assertEquals("colonel@repliforce.com", customUser.getEmail());
        assertEquals("colonel123", customUser.getPassword());
        assertTrue(customUser.getRoles().isEmpty());
    }

    @Test
    void getUsername() {
        assertEquals("iris@repliforce.com", user.getUsername());
        assertEquals(user.getEmail(), user.getUsername());
    }

    @Test
    void isAccountNonExpired() {
        assertTrue(user.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked() {
        assertTrue(user.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpired() {
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    void isEnabled() {
        assertTrue(user.isEnabled());
    }

    @Test
    void getAuthorities() {
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        assertNotNull(authorities);
        assertTrue(authorities.isEmpty());

        user.getRoles().add(hunterRole);
        user.getRoles().add(navigatorRole);

        authorities = user.getAuthorities();
        assertNotNull(authorities);
        assertEquals(2, authorities.size());

        assertTrue(authorities.stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_HUNTER")));
        assertTrue(authorities.stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_NAVIGATOR")));
    }
}