package dev.sachith.simplehttpapi.service.impl;

import dev.sachith.simplehttpapi.validation.HelloValidationRule;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HelloValidationServiceImplTest {

    @Test
    void returnsEmptyWhenNoRules() {
        HelloValidationServiceImpl service = new HelloValidationServiceImpl(List.of());
        assertEquals(Optional.empty(), service.validate("alice"));
    }

    @Test
    void returnsFirstErrorAndShortCircuits() {
        HelloValidationRule rule1 = mock(HelloValidationRule.class);
        HelloValidationRule rule2 = mock(HelloValidationRule.class);

        when(rule1.validate("bob")).thenReturn(Optional.of("error-1"));

        HelloValidationServiceImpl service = new HelloValidationServiceImpl(List.of(rule1, rule2));

        Optional<String> result = service.validate("bob");

        assertTrue(result.isPresent());
        assertEquals("error-1", result.get());
        verify(rule1, times(1)).validate("bob");
        verify(rule2, never()).validate(Mockito.any());
    }

    @Test
    void returnsEmptyWhenAllRulesPass() {
        HelloValidationRule rule1 = mock(HelloValidationRule.class);
        HelloValidationRule rule2 = mock(HelloValidationRule.class);

        when(rule1.validate("alice")).thenReturn(Optional.empty());
        when(rule2.validate("alice")).thenReturn(Optional.empty());

        HelloValidationServiceImpl service = new HelloValidationServiceImpl(List.of(rule1, rule2));

        Optional<String> result = service.validate("alice");
        assertTrue(result.isEmpty());
        verify(rule1, times(1)).validate("alice");
        verify(rule2, times(1)).validate("alice");
    }
}
