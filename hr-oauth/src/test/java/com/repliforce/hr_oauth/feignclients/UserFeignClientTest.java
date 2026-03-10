package com.repliforce.hr_oauth.feignclients;

import com.repliforce.hr_oauth.entities.Role;
import com.repliforce.hr_oauth.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserFeignClientTest {

    @Mock
    private UserFeignClient userFeignClient;

    private User user;
    private Role role;

    @BeforeEach
    void setUp() {
        user = new User(1L, "Colonel", "colonel@repliforce.com", "colonel123");
        role = new Role(1L, "ROLE_COMMANDER");
        user.getRoles().add(role);
    }

    @AfterEach
    void tearDown() {
        user = null;
        role = null;
        userFeignClient = null;
    }

    @Test
    void findByEmailSuccess() {
        String email = "colonel@repliforce.com";
        ResponseEntity<User> response = new ResponseEntity<>(user, HttpStatus.OK);

        when(userFeignClient.findByEmail(email)).thenReturn(response);

        ResponseEntity<User> result = userFeignClient.findByEmail(email);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("Colonel", result.getBody().getName());
        assertEquals("colonel@repliforce.com", result.getBody().getEmail());
        assertEquals(1, result.getBody().getRoles().size());

        verify(userFeignClient, times(1)).findByEmail(email);
    }

    @Test
    void findByEmailDifferentEmail() {
        String emailWanted = "iris@repliforce.com";
        String emailExpected = "colonel@repliforce.com";

        ResponseEntity<User> response = new ResponseEntity<>(user, HttpStatus.OK);
        when(userFeignClient.findByEmail(emailWanted)).thenReturn(response);

        ResponseEntity<User> result = userFeignClient.findByEmail(emailWanted);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertNotEquals(emailWanted, result.getBody().getEmail());
        assertEquals(emailExpected, result.getBody().getEmail());

        verify(userFeignClient, times(1)).findByEmail(emailWanted);
    }

    @Test
    void findByEmailNotFound() {
        String email = "sigma@mavhunter.com";

        when(userFeignClient.findByEmail(email)).thenReturn(
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
        );

        ResponseEntity<User> result = userFeignClient.findByEmail(email);

        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());

        verify(userFeignClient, times(1)).findByEmail(email);
    }

    @Test
    void findByEmailWithMultipleRoles() {
        Role adminRole = new Role(2L, "ROLE_HUNTER");
        user.getRoles().add(adminRole);

        String email = "colonel@repliforce.com";
        ResponseEntity<User> response = new ResponseEntity<>(user, HttpStatus.OK);

        when(userFeignClient.findByEmail(email)).thenReturn(response);

        ResponseEntity<User> result = userFeignClient.findByEmail(email);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().getRoles().size());
        assertTrue(result.getBody().getRoles().stream()
                .anyMatch(r -> r.getRoleName().equals("ROLE_HUNTER")));
        assertTrue(result.getBody().getRoles().stream()
                .anyMatch(r -> r.getRoleName().equals("ROLE_COMMANDER")));

        verify(userFeignClient, times(1)).findByEmail(email);
    }

    @Test
    void findByEmailParameterVerification() {
        String email = "hunter@mavhunter.com";
        when(userFeignClient.findByEmail(anyString()))
                .thenReturn(new ResponseEntity<>(user, HttpStatus.OK));

        userFeignClient.findByEmail(email);

        verify(userFeignClient).findByEmail(email);
        verify(userFeignClient, never()).findByEmail("navigator@mavhunter.com");
    }
}