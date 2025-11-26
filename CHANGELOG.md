# Changelog

All notable changes to this project will be documented in this file.

## [Unreleased]
- No changes yet.

## [0.0.1] - 2025-11-26
### Added
- Minimal Spring Boot HTTP API exposing GET /hello-world?name={name}.
- GreetingService and implementation to produce formatted "Hello {Name}" messages.
- Validation pipeline (Strategy/Chain-of-Responsibility) with rules:
  - EmptyHelloValidationRule: rejects null/empty/blank names.
  - AlphabetHalfValidationRule: rejects names starting with N–Z and non-letter starts.
- Consistent error response model {"error":"Invalid Input"} and success model {"message":"Hello {Name}"}.
- Unit tests for controller and validation rules.

### Build/Tooling
- Maven project (Java 21).
- Spring Boot 3.3.5 starter configuration.
- Checkstyle (Google style) and SpotBugs configuration in the build.

---
Notes:
- Default server port: 8080.
- Snapshot version in pom: 0.0.1-SNAPSHOT.
