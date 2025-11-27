package dev.sachith.simplehttpapi.validation;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AlphabetHalfValidationRuleTest {

    private final AlphabetHalfValidationRule rule = new AlphabetHalfValidationRule();

    @Test
    void allowsFirstHalfLettersAtoM() {
        assertEquals(Optional.empty(), rule.validate("Alice"));
        assertEquals(Optional.empty(), rule.validate("mario"));
        assertEquals(Optional.empty(), rule.validate("M"));
    }

    @Test
    void rejectsSecondHalfLettersNtoZ() {
        assertTrue(rule.validate("Nina").isPresent());
        assertTrue(rule.validate("zara").isPresent());
        assertTrue(rule.validate("Z").isPresent());
    }

    @Test
    void rejectsNonLetterStart() {
        assertTrue(rule.validate("1alice").isPresent());
        assertTrue(rule.validate("_bob").isPresent());
    }

    @Test
    void ignoresNullOrEmptyLetOtherRulesHandle() {
        assertEquals(Optional.empty(), rule.validate(null));
        assertEquals(Optional.empty(), rule.validate("   "));
    }
}
