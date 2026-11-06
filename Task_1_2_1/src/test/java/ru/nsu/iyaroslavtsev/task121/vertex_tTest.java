package ru.nsu.iyaroslavtsev.task121;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class vertex_tTest {

    @Test
    void containsSupportedTypes() {
        assertArrayEquals(
                new vertex_t[]{vertex_t.STRING, vertex_t.INTEGER},
                vertex_t.values()
        );
    }

    @Test
    void valueOfReturnsExpectedType() {
        assertEquals(vertex_t.STRING, vertex_t.valueOf("STRING"));
        assertEquals(vertex_t.INTEGER, vertex_t.valueOf("INTEGER"));
    }

    @Test
    void valueOfThrowsForUnknownType() {
        assertThrows(IllegalArgumentException.class,
                () -> vertex_t.valueOf("FLOAT"));
    }
}