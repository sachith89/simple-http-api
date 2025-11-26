package dev.sachith.simplehttpapi.api;

import dev.sachith.simplehttpapi.model.ErrorResponse;
import dev.sachith.simplehttpapi.model.HelloResponse;
import dev.sachith.simplehttpapi.service.GreetingService;
import dev.sachith.simplehttpapi.service.HelloValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author sachith
 */
@RestController
@RequestMapping
@RequiredArgsConstructor
public class HelloWorldController {

    private final GreetingService greetingService;
    private final HelloValidationService validationService;

    @GetMapping("/hello-world")
    public ResponseEntity<?> hello(@RequestParam(value = "name", required = false) String name) {

        Optional<String> validationError = validationService.validate(name);

        if (validationError.isPresent()) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Invalid Input"));
        }

        String message = greetingService.greet(name);
        return ResponseEntity.ok(new HelloResponse(message));
    }
}
