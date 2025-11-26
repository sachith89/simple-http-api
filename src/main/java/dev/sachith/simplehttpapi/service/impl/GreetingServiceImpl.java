package dev.sachith.simplehttpapi.service.impl;

import dev.sachith.simplehttpapi.service.GreetingService;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * @author sachith
 */
@Service
public class GreetingServiceImpl implements GreetingService {

    @Override
    public String greet(String rawName) {

        String name = rawName == null ? "" : rawName.trim();

        if (name.isEmpty()) {
            return "Hello";
        }

        return "Hello " + capitalize(name);
    }

    @Override
    public String capitalize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase(Locale.ROOT) + input.substring(1).toLowerCase(Locale.ROOT);
    }

}
