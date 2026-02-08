package com.repliforce.hruser.resources;

import com.repliforce.hruser.entities.User;
import com.repliforce.hruser.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

class UserResourceTest {
    @Mock
    private Environment environment;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserResource userResource;

    private AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
        reset(userRepository, environment);
    }

    @Test
    void findById() {
        User user = mock(User.class);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        ResponseEntity<User> response = userResource.findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSame(user, response.getBody());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById() {
        User user = mock(User.class);
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(user);

        ResponseEntity<User> response = userResource.findById(email);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSame(user, response.getBody());
        verify(userRepository, times(1)).findByEmail(email);
    }
}