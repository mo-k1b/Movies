package com.example.movies.controller;

import com.example.movies.model.User;
import com.example.movies.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void registerUser_Success() throws Exception {
        mockMvc.perform(post("/register")
                        .param("email", "newuser@example.com")
                        .param("password", "password123")
                        .param("phoneNumber", "1234567890"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        assertTrue(userRepository.findByEmail("newuser@example.com").isPresent());
    }

    @Test
    void registerUser_DuplicateEmail() throws Exception {
        // Arrange: Create existing user
        User existingUser = new User();
        existingUser.setEmail("existing@example.com");
        existingUser.setPassword("pass");
        existingUser.setUsername("ExistingUser");
        userRepository.save(existingUser);

        // Act & Assert
        mockMvc.perform(post("/register")
                        .param("email", "existing@example.com") // Same email
                        .param("password", "newpass")
                        .param("phoneNumber", "0987654321"))
                .andExpect(status().isOk()) // Should return to registration page
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Email already in use."));
    }
}
