package dev.sachith.simplehttpapi.validation;

import java.util.Optional;

/**
 * Strategy interface for validation.
 * Implementations return Optional.empty() if valid, otherwise a reason.
 *
 * @author sachith
 */
public sealed interface HelloValidationRule permits AlphabetHalfValidationRule, EmptyHelloValidationRule {
    Optional<String> validate(String name);
}
