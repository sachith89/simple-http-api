package dev.sachith.simplehttpapi.validation;

import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author sachith
 */
@Component
public non-sealed class EmptyHelloValidationRule implements HelloValidationRule {

    @Override
    public Optional<String> validate(String name) {
        if (name == null) return Optional.of("name is null");
        if (name.trim().isEmpty()) return Optional.of("name is empty");
        return Optional.empty();
    }
}
