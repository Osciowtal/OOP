package ru.nsu.iyaroslavtsev.task113;

import java.util.Map;

/**
 * Mul class.
 */
class Mul extends Expression {

    private final Expression left;
    private final Expression right;

    /**
     * Sets left / right expressions.
     *
     * @param l - left.
     * @param r - right.
     */
    public Mul(Expression l, Expression r) {
        left = l;
        right = r;
    }

    @Override public void insert(StringBuilder expr) {
        expr.append('(');
        left.insert(expr);
        expr.append('*');
        right.insert(expr);
        expr.append(')');
    }

    // (u*v)' = u'*v + u*v'
    @Override public Expression derivative(String var) {
        return new Add(
                new Mul(left.derivative(var), right),
                new Mul(left, right.derivative(var))
        );
    }

    @Override public double eval(Map<String, Double> vars) {
        return left.eval(vars) * right.eval(vars);
    }
}