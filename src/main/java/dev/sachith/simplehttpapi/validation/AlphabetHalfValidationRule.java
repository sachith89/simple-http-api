package dev.sachith.simplehttpapi.validation;

import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author sachith
 */
@Component
public non-sealed class AlphabetHalfValidationRule implements HelloValidationRule {

    @Override
    public Optional<String> validate(String name) {
        if (name == null || name.trim().isEmpty()) {
            return Optional.empty();
        }
        String trimmed = name.trim();
        char first = trimmed.charAt(0);
        if (!Character.isLetter(first)) {
            return Optional.of("name must start with a letter");
        }
        char upper = Character.toUpperCase(first);
        if (upper >= 'N' && upper <= 'Z') {
            return Optional.of("name starts with second half letter");
        }
        // A-M inclusive are valid
        return Optional.empty();
    }
}
