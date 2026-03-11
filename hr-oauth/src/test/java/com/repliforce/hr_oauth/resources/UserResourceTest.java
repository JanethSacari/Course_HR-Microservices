package com.repliforce.hr_oauth.resources;

import com.repliforce.hr_oauth.entities.Role;
import com.repliforce.hr_oauth.entities.User;
import com.repliforce.hr_oauth.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserResourceTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserResource userResource;

    private MockMvc mockMvc;
    private User user;
    private Role role;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userResource).build();

        user = new User(1L, "Iris", "iris@repliforce.com", "iris123");
        role = new Role(1L, "ROLE_NAVIGATOR");
        user.getRoles().add(role);
    }

    @AfterEach
    void tearDown() {
        mockMvc = null;
        user = null;
        role = null;
    }

    @Test
    void testFindByEmailSuccess() throws Exception {
        String email = "iris@repliforce.com";

        when(userService.findByEmail(email)).thenReturn(user);

        mockMvc.perform(get("/users/search")
                .param("email", email)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Iris")))
                .andExpect(jsonPath("$.email", is("iris@repliforce.com")))
                .andExpect(jsonPath("$.roles", hasSize(1)))
                .andExpect(jsonPath("$.roles[0].roleName", is("ROLE_NAVIGATOR")));

        verify(userService, times(1)).findByEmail(email);
    }

    @Test
    void testFindByEmailNotFound() throws Exception {
        String email = "notfound@example.com";

        when(userService.findByEmail(email))
                .thenThrow(new IllegalArgumentException("User not found"));

        mockMvc.perform(get("/users/search")
                .param("email", email)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(userService, times(1)).findByEmail(email);
    }

    @Test
    void testFindByEmailWithMultipleRoles() throws Exception {
        Role adminRole = new Role(2L, "ROLE_HUNTER");
        user.getRoles().add(adminRole);

        String email = "iris@repliforce.com";

        when(userService.findByEmail(email)).thenReturn(user);

        mockMvc.perform(get("/users/search")
                .param("email", email)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roles", hasSize(2)))
                .andExpect(jsonPath("$.roles[*].roleName", containsInAnyOrder("ROLE_NAVIGATOR", "ROLE_HUNTER")));

        verify(userService, times(1)).findByEmail(email);
    }

    @Test
    void testFindByEmailWithoutParameter() throws Exception {
        mockMvc.perform(get("/users/search")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(userService, never()).findByEmail(anyString());
    }

    @Test
    void testFindByEmailCompleteResponse() throws Exception {
        String email = "iris@example.com";

        when(userService.findByEmail(email)).thenReturn(user);

        mockMvc.perform(get("/users/search")
                .param("email", email)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", notNullValue()))
                .andExpect(jsonPath("$.email", notNullValue()))
                .andExpect(jsonPath("$.roles", notNullValue()));

        verify(userService, times(1)).findByEmail(email);
    }

    @Test
    void testFindByEmailVerifyParameter() throws Exception {
        String email = "test@example.com";

        when(userService.findByEmail(email)).thenReturn(user);

        mockMvc.perform(get("/users/search")
                .param("email", email)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService).findByEmail(email);
        verify(userService, never()).findByEmail("another@example.com");
    }
}