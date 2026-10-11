package ru.nsu.iyaroslavtsev.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;


class VariableTest {

    @Test
    void insert_writesName() {
        StringBuilder sb = new StringBuilder();
        new Variable("x").insert(sb);
        assertEquals("x", sb.toString());
    }

    @Test
    void insert_multiLetterName() {
        StringBuilder sb = new StringBuilder();
        new Variable("counter").insert(sb);
        assertEquals("counter", sb.toString());
    }

    @Test
    void derivative_sameNameIsOne() {
        StringBuilder sb = new StringBuilder();
        new Variable("x").derivative("x").insert(sb);
        assertEquals("1.0", sb.toString());
    }

    @Test
    void derivative_otherNameIsZero() {
        StringBuilder sb = new StringBuilder();
        new Variable("x").derivative("y").insert(sb);
        assertEquals("0.0", sb.toString());
    }

    @Test
    void eval_looksUpValue() {
        Map<String, Double> vars = new HashMap<>();
        vars.put("x", 10.0);
        assertEquals(10.0, new Variable("x").eval(vars));
    }

    @Test
    void eval_stringOverload_parsesAssignment() {
        assertEquals(42.0, new Variable("counter").eval("counter=42"));
    }
}