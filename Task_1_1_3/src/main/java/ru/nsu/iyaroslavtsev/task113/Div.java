package ru.nsu.iyaroslavtsev.task113;

import java.util.Map;

class Div extends Expression {
    private final Expression left, right;
    public Div(Expression l, Expression r) {
        left = l; right = r;
    }

    @Override public void insert(StringBuilder expr) {
        expr.append('(');
        left.insert(expr);
        expr.append('/');
        right.insert(expr);
        expr.append(')');
    }

    // (u/v)' = (u'*v - u*v') / (v^2)
    @Override public Expression derivative(String var) {
        Expression numerator = new Sub(
                new Mul(left.derivative(var), right),
                new Mul(left, right.derivative(var))
        );
        Expression denominator = new Mul(right, right);
        return new Div(numerator, denominator);
    }

    @Override public double eval(Map<String, Double> vars) {
        return left.eval(vars) / right.eval(vars);
    }
}