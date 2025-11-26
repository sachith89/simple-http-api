package dev.sachith.simplehttpapi.service;

import java.util.Optional;

public interface HelloValidationService {
    Optional<String> validate(String name);
}
