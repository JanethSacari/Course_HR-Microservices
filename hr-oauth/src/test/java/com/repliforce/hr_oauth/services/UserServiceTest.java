package com.repliforce.hr_oauth.services;

import com.repliforce.hr_oauth.entities.Role;
import com.repliforce.hr_oauth.entities.User;
import com.repliforce.hr_oauth.feignclients.UserFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserFeignClient userFeignClient;

    @InjectMocks
    private UserService userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User(1L, "Test User", "iris@repliforce.com", "password123");
        Role role = new Role(1L, "ROLE_NAVIGATOR");
        testUser.getRoles().add(role);
    }


    @Test
    void findByEmail() {
        String email = "iris@repliforce.com";
        when(userFeignClient.findByEmail(email))
                .thenReturn(ResponseEntity.ok(testUser));

        User result = userService.findByEmail(email);

        assertNotNull(result);
        assertEquals(testUser.getId(), result.getId());
        assertEquals(testUser.getEmail(), result.getEmail());
        assertEquals(testUser.getName(), result.getName());
        verify(userFeignClient, times(1)).findByEmail(email);
    }

    @Test
    void findByEmailThrowsException() {
        String email = "notfound@example.com";
        when(userFeignClient.findByEmail(email))
                .thenReturn(ResponseEntity.ok(null));

        assertThrows(IllegalArgumentException.class, () -> userService.findByEmail(email));
        verify(userFeignClient, times(1)).findByEmail(email);
    }

    @Test
    void loadUserByUsername() {
        String email = "iris@repliforce.com";
        when(userFeignClient.findByEmail(email))
                .thenReturn(ResponseEntity.ok(testUser));
        assertDoesNotThrow(() -> userService.loadUserByUsername(email));
        verify(userFeignClient, times(1)).findByEmail(email);
    }

    @Test
    void loadUserByUsernameThrowsException() {
        String email = null;
        when(userFeignClient.findByEmail(email))
                .thenReturn(ResponseEntity.ok(null));
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(email));
        verify(userFeignClient, times(1)).findByEmail(email);
    }
}