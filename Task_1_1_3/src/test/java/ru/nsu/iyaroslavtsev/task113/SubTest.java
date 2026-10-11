package ru.nsu.iyaroslavtsev.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import org.junit.jupiter.api.Test;


class SubTest {

    @Test
    void insert_wrapsInParens() {
        StringBuilder sb = new StringBuilder();
        new Sub(new Numberr(5), new Numberr(3)).insert(sb);
        assertEquals("(5.0-3.0)", sb.toString());
    }

    @Test
    void eval_subtracts() {
        assertEquals(2.0, new Sub(new Numberr(5), new Numberr(3)).eval(new HashMap<>()));
    }

    @Test
    void eval_negativeResult() {
        assertEquals(-3.0, new Sub(new Numberr(2), new Numberr(5)).eval(new HashMap<>()));
    }

    @Test
    void derivative_isDifferenceOfDerivatives() {
        StringBuilder sb = new StringBuilder();
        new Sub(new Variable("x"), new Variable("y"))
                .derivative("x")
                .insert(sb);
        assertEquals("(1.0-0.0)", sb.toString());
    }

    @Test
    void eval_withVariables() {
        Expression e = new Sub(new Variable("x"), new Numberr(3));
        assertEquals(7.0, e.eval("x=10"));
    }
}