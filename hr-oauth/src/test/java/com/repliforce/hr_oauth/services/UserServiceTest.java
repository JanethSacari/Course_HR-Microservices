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
        testUser = new User(1L, "Test User", "test@example.com", "password123");
        Role role = new Role(1L, "ROLE_USER");
        testUser.getRoles().add(role);
    }


    @Test
    void findByEmail() {
        String email = "test@example.com";
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
}