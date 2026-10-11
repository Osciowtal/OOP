package ru.nsu.iyaroslavtsev.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;


class NumberrTest {

    @Test
    void insert_writesInteger() {
        StringBuilder sb = new StringBuilder();
        new Numberr(3).insert(sb);
        assertEquals("3.0", sb.toString());
    }

    @Test
    void insert_writesFraction() {
        StringBuilder sb = new StringBuilder();
        new Numberr(3.111).insert(sb);
        assertEquals("3.111", sb.toString());
    }

    @Test
    void insert_writesZero() {
        StringBuilder sb = new StringBuilder();
        new Numberr(0).insert(sb);
        assertEquals("0.0", sb.toString());
    }

    @Test
    // (5)' = 0
    void derivative_isZero() {
        StringBuilder sb = new StringBuilder();
        new Numberr(5).derivative("x").insert(sb);
        assertEquals("0.0", sb.toString());
    }

    @Test
    void derivative_ignoresVariableName() {
        StringBuilder sb = new StringBuilder();
        new Numberr(7).derivative("anything").insert(sb);
        assertEquals("0.0", sb.toString());
    }

    @Test
    void eval_returnsValue() {
        assertEquals(7.5, new Numberr(7.5).eval(new HashMap<>()));
    }

    @Test
    void eval_ignoresVarsMap() {
        Map<String, Double> vars = new HashMap<>();
        vars.put("x", 100.0);
        assertEquals(3.0, new Numberr(3).eval(vars), 1e-9);
    }

    @Test
    void eval_stringOverload() {
        assertEquals(42.0, new Numberr(42).eval("x=10; y=20"));
    }

    @Test
    void eval_assignments() {
        Expression e = new Add(new Variable("x1"), new Variable("x2"));
        assertEquals(12.0, e.eval("x1=5;x2=7"));
    }
}