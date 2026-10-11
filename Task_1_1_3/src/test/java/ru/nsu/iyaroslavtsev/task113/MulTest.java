package ru.nsu.iyaroslavtsev.task113;

import org.junit.jupiter.api.Test;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MulTest {

    @Test
    void insert_wrapsInParens() {
        StringBuilder sb = new StringBuilder();
        new Mul(new Numberr(3), new Numberr(4)).insert(sb);
        assertEquals("(3.0*4.0)", sb.toString());
    }

    @Test
    void eval_multiplies() {
        assertEquals(12.0, new Mul(new Numberr(3), new Numberr(4)).eval(new HashMap<>()));
    }

    @Test
    void eval_withVariables() {
        Expression e = new Mul(new Numberr(2), new Variable("x"));
        assertEquals(20.0, e.eval("x=10"));
    }

    @Test
    void derivative_productRule() {
        StringBuilder sb = new StringBuilder();
        new Mul(new Numberr(2), new Variable("x"))
                .derivative("x")
                .insert(sb);
        assertEquals("((0.0*x)+(2.0*1.0))", sb.toString());
    }

    @Test
    void derivative_productRuleBothVariables() {
        StringBuilder sb = new StringBuilder();
        new Mul(new Variable("x"), new Variable("x"))
                .derivative("x")
                .insert(sb);
        assertEquals("((1.0*x)+(x*1.0))", sb.toString());
    }

    @Test
    void derivative_zeroTimesNumberIsZero() {
        Expression e = new Mul(new Numberr(5), new Numberr(3));
        assertEquals(0.0, e.derivative("x").eval(new HashMap<>()), 1e-9);
    }
}