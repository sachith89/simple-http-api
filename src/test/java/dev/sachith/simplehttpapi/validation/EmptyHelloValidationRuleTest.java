package dev.sachith.simplehttpapi.validation;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EmptyHelloValidationRuleTest {

    private final EmptyHelloValidationRule rule = new EmptyHelloValidationRule();

    @Test
    void returnsErrorWhenNull() {
        Optional<String> result = rule.validate(null);
        assertTrue(result.isPresent());
        assertEquals("name is null", result.get());
    }

    @Test
    void returnsErrorWhenBlank() {
        Optional<String> result = rule.validate("   \t  ");
        assertTrue(result.isPresent());
        assertEquals("name is empty", result.get());
    }

    @Test
    void passesWhenNonBlank() {
        Optional<String> result = rule.validate("alice");
        assertTrue(result.isEmpty());
    }
}
