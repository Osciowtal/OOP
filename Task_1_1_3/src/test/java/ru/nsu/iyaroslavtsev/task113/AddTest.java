package ru.nsu.iyaroslavtsev.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;


class AddTest {

    @Test
    void insert_wrapsInParens() {
        StringBuilder sb = new StringBuilder();
        new Add(new Numberr(1), new Numberr(2)).insert(sb);
        assertEquals("(1.0+2.0)", sb.toString());
    }

    @Test
    void eval_sums() {
        assertEquals(5.0, new Add(new Numberr(2), new Numberr(3)).eval(new HashMap<>()));
    }

    @Test
    void eval_withVariables() {
        Map<String, Double> vars = new HashMap<>();
        vars.put("x", 10.0);
        vars.put("y", 5.0);
        Expression e = new Add(new Variable("x"), new Variable("y"));
        assertEquals(15.0, e.eval(vars), 1e-9);
    }

    @Test
    void eval_stringOverload() {
        Expression e = new Add(new Numberr(3), new Variable("x"));
        assertEquals(13.0, e.eval("x=10"));
    }

    @Test
    void derivative_isSumOfDerivatives() {
        StringBuilder sb = new StringBuilder();
        new Add(new Variable("x"), new Numberr(5))
                .derivative("x")
                .insert(sb);
        assertEquals("(1.0+0.0)", sb.toString());
    }

    @Test
    void derivative_doesNotMutateOriginal() {
        Expression orig = new Add(new Numberr(1), new Variable("x"));
        orig.derivative("x");
        StringBuilder sb = new StringBuilder();
        orig.insert(sb);
        assertEquals("(1.0+x)", sb.toString());
    }
}