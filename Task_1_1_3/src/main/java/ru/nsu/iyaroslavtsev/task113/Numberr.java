package ru.nsu.iyaroslavtsev.task113;

import java.util.Map;

/**
 * Numberr class.
 */
class Numberr extends Expression {

    private final double value;

    /**
     * Sets value.
     *
     * @param value - value.
     */
    public Numberr(double value) {
        this.value = value;
    }

    @Override public void insert(StringBuilder expr) {
        expr.append(value);
    }

    @Override public Expression derivative(String var) {
        return new Numberr(0);
    }

    @Override public double eval(Map<String, Double> vars) {
        return value;
    }
}