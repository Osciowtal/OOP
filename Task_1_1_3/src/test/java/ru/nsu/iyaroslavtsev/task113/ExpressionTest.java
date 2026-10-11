package ru.nsu.iyaroslavtsev.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import org.junit.jupiter.api.Test;


class ExpressionTest {

    private final Expression expr = new Numberr(0);

    @Test
    void parseArgs_single() {
        Map<String, Double> m = expr.parseArgs("x=10");
        assertEquals(1, m.size());
        assertEquals(10.0, m.get("x"));
    }

    @Test
    void parseArgs_multiple() {
        Map<String, Double> m = expr.parseArgs("x=10;y=20.5");
        assertEquals(2, m.size());
        assertEquals(10.0, m.get("x"));
        assertEquals(20.5, m.get("y"));
    }

    @Test
    void parseArgs_handlesSpaces() {
        Map<String, Double> m = expr.parseArgs(" x = 10 ; y = 20 ");
        assertEquals(10.0, m.get("x"));
        assertEquals(20.0, m.get("y"));
    }

    @Test
    void parseArgs_skipsEmptyParts() {
        Map<String, Double> m = expr.parseArgs(";;x=1;;");
        assertEquals(1, m.size());
        assertEquals(1.0, m.get("x"));
    }

    @Test
    void parseArgs_emptyString() {
        Map<String, Double> m = expr.parseArgs("");
        assertTrue(m.isEmpty());
    }

    @Test
    void parseArgs_multiLetterName() {
        Map<String, Double> m = expr.parseArgs("counter=7");
        assertEquals(7.0, m.get("counter"));
    }
}