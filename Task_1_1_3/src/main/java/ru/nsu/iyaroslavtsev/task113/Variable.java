package ru.nsu.iyaroslavtsev.task113;

import java.util.Map;

/**
 * Variable class.
 */
class Variable extends Expression {

    private final String name;

    /**
     * Sets name.
     *
     * @param name - name.
     */
    public Variable(String name) {
        this.name = name;
    }

    @Override public void insert(StringBuilder expr) {
        expr.append(name);
    }

    @Override public Expression derivative(String var) {
        return new Numberr(name.equals(var) ? 1 : 0);
    }

    @Override public double eval(Map<String, Double> vars) {
        Double v = vars.get(name);
        if (v == null) {
            throw new IllegalArgumentException("Var '" + name + "' isn't set");
        }
        return v;
    }
}
