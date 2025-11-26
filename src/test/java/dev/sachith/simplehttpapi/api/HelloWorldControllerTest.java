package dev.sachith.simplehttpapi.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import dev.sachith.simplehttpapi.service.GreetingService;
import dev.sachith.simplehttpapi.service.HelloValidationService;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = HelloWorldController.class)
class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HelloValidationService validationService;

    @MockBean
    private GreetingService greetingService;

    @Test
    @DisplayName("Returns 200 and message for names A-M")
    void validFirstHalf() throws Exception {
        when(validationService.validate("alice")).thenReturn(Optional.empty());
        when(greetingService.greet("alice")).thenReturn("Hello Alice");

        mockMvc.perform(get("/hello-world").param("name", "alice"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"message\":\"Hello Alice\"}"));
    }

    @Test
    @DisplayName("Returns 400 for names starting N-Z")
    void invalidSecondHalf() throws Exception {
        when(validationService.validate("zara")).thenReturn(Optional.of("error"));

        mockMvc.perform(get("/hello-world").param("name", "zara"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"error\":\"Invalid Input\"}"));
    }

    @Test
    @DisplayName("Returns 400 when name param is missing")
    void missingParam() throws Exception {
        when(validationService.validate(null)).thenReturn(Optional.of("missing"));

        mockMvc.perform(get("/hello-world"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"error\":\"Invalid Input\"}"));
    }

    @Test
    @DisplayName("Returns 400 when name is empty or whitespace")
    void emptyOrWhitespace() throws Exception {
        when(validationService.validate("   ")).thenReturn(Optional.of("empty"));

        mockMvc.perform(get("/hello-world").param("name", "   "))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"error\":\"Invalid Input\"}"));
    }

    @Test
    @DisplayName("Returns 400 when name doesn't start with a letter")
    void nonLetterStart() throws Exception {
        when(validationService.validate("1alice")).thenReturn(Optional.of("non-letter"));

        mockMvc.perform(get("/hello-world").param("name", "1alice"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"error\":\"Invalid Input\"}"));
    }
}
