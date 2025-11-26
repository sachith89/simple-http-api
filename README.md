[![Java CI with Maven](https://github.com/sachith89/simple-http-api/actions/workflows/maven.yml/badge.svg)](https://github.com/sachith89/simple-http-api/actions/workflows/maven.yml)

# Simple HTTP API

A minimal Spring Boot application exposing a single endpoint to greet users based on input rules.

### Endpoint
- GET /hello-world?name=alice

### Behavior
1. If the first letter of name is in A–M (case-insensitive):
   - 200 OK, body: { "message": "Hello Alice" }
2. If the first letter is in N–Z (case-insensitive):
   - 400 Bad Request, body: { "error": "Invalid Input" }
3. If name is missing, empty, or does not start with a letter:
   - 400 Bad Request, body: { "error": "Invalid Input" }

### Tech stack
- Java 21
- Spring Boot 3.3.5 (spring-boot-starter-web)
- Maven
- JUnit 5 + Spring Test

### How to run
1. Build and run tests:
   - mvn clean verify
2. Run the application:
   - mvn spring-boot:run
3. Try it:
   - curl 'http://localhost:8080/hello-world?name=alice'

### How to run tests
- mvn test

## Design notes and assumptions
- Clean code and patterns:
  - Controller delegates to a GreetingService and a NameValidationService.
  - Validation uses the Strategy/Chain-of-Responsibility pattern via HelloValidationRule implementations:
    - EmptyNameValidationRule: rejects null/empty/whitespace-only inputs.
    - AlphabetHalfValidationRule: rejects names starting with N–Z and non-letter starting characters.
  - The validator applies rules in order and returns the first error cause; controller maps any error to a uniform {"error":"Invalid Input"} as per spec.
- Greeting formatting: capitalizes the first letter and lowercases the rest ("alice" -> "Alice").
- Port: defaults to 8080.
