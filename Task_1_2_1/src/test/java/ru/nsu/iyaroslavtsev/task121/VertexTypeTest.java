package ru.nsu.iyaroslavtsev.task121;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class VertexTypeTest {

    @Test
    void containsSupportedTypes() {
        assertArrayEquals(
                new VertexType[]{VertexType.STRING, VertexType.INTEGER},
                VertexType.values()
        );
    }

    @Test
    void valueOfReturnsExpectedType() {
        assertEquals(VertexType.STRING, VertexType.valueOf("STRING"));
        assertEquals(VertexType.INTEGER, VertexType.valueOf("INTEGER"));
    }

    @Test
    void valueOfThrowsForUnknownType() {
        assertThrows(IllegalArgumentException.class,
                () -> VertexType.valueOf("FLOAT"));
    }
}