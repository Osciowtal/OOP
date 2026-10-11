package ru.nsu.iyaroslavtsev.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import org.junit.jupiter.api.Test;


class DivTest {

    @Test
    void insert_wrapsInParens() {
        StringBuilder sb = new StringBuilder();
        new Div(new Numberr(10), new Numberr(2)).insert(sb);
        assertEquals("(10.0/2.0)", sb.toString());
    }

    @Test
    void eval_divides() {
        assertEquals(5.0, new Div(new Numberr(10), new Numberr(2)).eval(new HashMap<>()));
    }

    @Test
    void eval_withVariables() {
        Expression e = new Div(new Variable("x"), new Numberr(2));
        assertEquals(5.0, e.eval("x=10"));
    }

    @Test
    void derivative_quotientRule() {
        StringBuilder sb = new StringBuilder();
        new Div(new Variable("x"), new Numberr(2))
                .derivative("x")
                .insert(sb);
        assertEquals("(((1.0*2.0)-(x*0.0))/(2.0*2.0))", sb.toString());
    }

    @Test
    void derivative_numericConstant() {
        Expression e = new Div(new Numberr(10), new Numberr(2));
        assertEquals(0.0, e.derivative("x").eval(new HashMap<>()));
    }
}