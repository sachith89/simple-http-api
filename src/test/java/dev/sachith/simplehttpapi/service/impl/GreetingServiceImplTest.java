package dev.sachith.simplehttpapi.service.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreetingServiceImplTest {

    private final GreetingServiceImpl service = new GreetingServiceImpl();

    @Test
    void greetReturnsHelloWhenNull() {
        assertEquals("Hello", service.greet(null));
    }

    @Test
    void greetReturnsHelloWhenBlank() {
        assertEquals("Hello", service.greet("   \n\t  "));
    }

    @Test
    void greetReturnsHelloWithCapitalizedName() {
        assertEquals("Hello Alice", service.greet("alice"));
        assertEquals("Hello Bob", service.greet("  bob  "));
        assertEquals("Hello John", service.greet("jOHN"));
    }

    @Test
    void capitalizeHandlesNullAndEmpty() {
        assertNull(service.capitalize(null));
        assertEquals("", service.capitalize(""));
    }

    @Test
    void capitalizeCapitalizesFirstAndLowercasesRest() {
        assertEquals("Alice", service.capitalize("alice"));
        assertEquals("Alice", service.capitalize("ALICE"));
        assertEquals("Alice", service.capitalize("aLiCe"));
    }
}
