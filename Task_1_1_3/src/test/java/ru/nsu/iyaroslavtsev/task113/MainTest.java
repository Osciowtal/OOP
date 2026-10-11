package ru.nsu.iyaroslavtsev.task113;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MainTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream capturedOut;

    @BeforeEach
    void setUp() {
        Main.variabled = 0;
        Main.want_to_assign = 0;
        capturedOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capturedOut));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    private void feedInput(String s) {
        System.setIn(new ByteArrayInputStream(s.getBytes()));
    }

    @Test
    void multiadd() {
        // (((x+1)+(x+2))+(x+3)) -> 1 (assign val) -> x=1 -> 1 (compute val)
        feedInput("(((x+1)+(x+2))+(x+3))\n1\nx=1\n1\n");
        Main.main(new String[]{});
        assertTrue(capturedOut.toString().contains("9.0"),
                "Output was:" + capturedOut);
    }

    @Test
    // (3+2) -> 1 (compute val)
    void evaluatesConstantExpression() {
        feedInput("(3+2)\n1\n");
        Main.main(new String[]{});
        assertTrue(capturedOut.toString().contains("5.0"),
                "Output was:" + capturedOut);
    }

    @Test
    void evaluatesExpressionWithVariables() {
        // (3+(2*x)) -> 1 (assign vals) -> x=10 -> 1 (compute val)
        feedInput("(3+(2*x))\n1\nx=10\n1\n");
        Main.main(new String[]{});
        assertTrue(capturedOut.toString().contains("23.0"),
                "Output was: " + capturedOut);
    }

    @Test
    void derivativeOnConstantExpressionSaysAlwaysZero() {
        // (3+2) -> 2 (derivative)
        feedInput("(3+2)\n2\n");
        Main.main(new String[]{});
        assertTrue(capturedOut.toString().contains("always 0.0"),
                "Output was: " + capturedOut);
    }

    @Test
    void derivativeOnVariableExpression() {
        // (3+(2*x)) -> 0 (not assign) -> 2 (derivative) -> "x" (by x)
        feedInput("(3+(2*x))\n0\n2\nx\n");
        Main.main(new String[]{});
        String output = capturedOut.toString();
        assertTrue(output.contains("(0.0+((0.0*x)+(2.0*1.0)))"),
                "Output was: " + output);
    }

    @Test
    void unknownOperationReportsError() {
        feedInput("(3+2)\n99\n");
        Main.main(new String[]{});
        assertTrue(capturedOut.toString().contains("Unrecognized option"),
                "Output was: " + capturedOut);
    }
}