package ru.nsu.iyaroslavtsev.task121;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class vertexParseTest {

    @Test
    void parseStringReturnsOriginalValue() {
        assertEquals("Vertex-A", vertexParse.parseString("Vertex-A"));
    }

    @Test
    void parseStringPreservesWhitespace() {
        assertEquals(" A ", vertexParse.parseString(" A "));
    }

    @Test
    void parseIntegerConvertsValue() {
        assertEquals(123, vertexParse.parseInteger("123"));
        assertEquals(-45, vertexParse.parseInteger("-45"));
    }

    @Test
    void parseIntegerThrowsForInvalidValue() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> vertexParse.parseInteger("ABC")
        );

        assertEquals("Invalid integer vertex: ABC", exception.getMessage());
        assertInstanceOf(NumberFormatException.class, exception.getCause());
    }
}