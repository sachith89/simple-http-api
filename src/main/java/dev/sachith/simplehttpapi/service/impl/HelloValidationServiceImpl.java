package dev.sachith.simplehttpapi.service.impl;

import dev.sachith.simplehttpapi.validation.HelloValidationRule;
import dev.sachith.simplehttpapi.service.HelloValidationService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author sachith
 */
@Service
public class HelloValidationServiceImpl implements HelloValidationService {

    private final List<HelloValidationRule> rules;

    public HelloValidationServiceImpl(List<HelloValidationRule> rules) {
        this.rules = Collections.unmodifiableList(rules);
    }

    @Override
    public Optional<String> validate(String name) {

        for (HelloValidationRule rule : rules) {

            Optional<String> result = rule.validate(name);

            if (result.isPresent()) {
                return result;
            }
        }

        return Optional.empty();
    }
}
