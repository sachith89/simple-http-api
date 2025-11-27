package dev.sachith.simplehttpapi.validation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EmptyHelloValidationRuleTest {

    @Autowired
    private EmptyHelloValidationRule emptyHelloValidationRule;

    @ParameterizedTest
    @CsvSource({
            "   , name is null",
            "'', name is empty"
    })
    void shouldReturnErrorMessageForInvalidInputs(String input, String expectedMessage) {
        Optional<String> result = emptyHelloValidationRule.validate(input);
        assertTrue(result.isPresent());
        assertEquals(expectedMessage, result.get());
    }

    @ParameterizedTest
    @CsvSource({
            "John",
            "John Doe",
            "Hello World"
    })
    void shouldReturnEmptyOptionalForValidInputs(String input) {
        Optional<String> result = emptyHelloValidationRule.validate(input);
        assertTrue(result.isEmpty());
    }
}