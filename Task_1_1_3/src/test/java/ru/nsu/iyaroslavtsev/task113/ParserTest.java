package ru.nsu.iyaroslavtsev.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ParserTest {

    @BeforeEach
    void resetGlobalFlag() {
        Main.variabled = 0;
    }

    @Test
    void parsesNumber() {
        StringBuilder sb = new StringBuilder();
        Parser.parse("42").insert(sb);
        assertEquals("42.0", sb.toString());
    }

    @Test
    void parsesFraction() {
        StringBuilder sb = new StringBuilder();
        Parser.parse("3.1").insert(sb);
        assertEquals("3.1", sb.toString());
    }

    @Test
    void parsesVariable() {
        StringBuilder sb = new StringBuilder();
        Parser.parse("varname").insert(sb);
        assertEquals("varname", sb.toString());
        assertEquals(1, Main.variabled);
    }

    @Test
    void parsesSimpleAdd() {
        StringBuilder sb = new StringBuilder();
        Parser.parse("(1+2)").insert(sb);
        assertEquals("(1.0+2.0)", sb.toString());
    }

    @Test
    void parsesAllOperators() {
        StringBuilder sb = new StringBuilder();
        Parser.parse("(1-2)").insert(sb);
        assertEquals("(1.0-2.0)", sb.toString());

        sb = new StringBuilder();
        Parser.parse("(1*2)").insert(sb);
        assertEquals("(1.0*2.0)", sb.toString());

        sb = new StringBuilder();
        Parser.parse("(1/2)").insert(sb);
        assertEquals("(1.0/2.0)", sb.toString());
    }

    @Test
    void parsesNestedExpression() {
        Expression e = Parser.parse("(3+(2*x))");
        StringBuilder sb = new StringBuilder();
        e.insert(sb);
        assertEquals("(3.0+(2.0*x))", sb.toString());
    }

    @Test
    void parsesDeepNesting() {
        Expression e = Parser.parse("((1+2)*(3-4))");
        StringBuilder sb = new StringBuilder();
        e.insert(sb);
        assertEquals("((1.0+2.0)*(3.0-4.0))", sb.toString());
    }

    @Test
    void skipsWhitespace() {
        Expression e = Parser.parse("  ( 3 + ( 2 * x ) )  ");
        StringBuilder sb = new StringBuilder();
        e.insert(sb);
        assertEquals("(3.0+(2.0*x))", sb.toString());
    }

    @Test
    void computedResult_matchesParsedExpression() {
        Expression e = Parser.parse("(3+(2*x))");
        assertEquals(23.0, e.eval("x=10"));
    }

    @Test
    void setsGlobalFlagOnVariable() {
        Main.variabled = 0;
        Parser.parse("x");
        assertEquals(1, Main.variabled);
    }

    @Test
    void doesNotSetGlobalFlagOnNumber() {
        Main.variabled = 0;
        Parser.parse("42");
        assertEquals(0, Main.variabled);
    }
}