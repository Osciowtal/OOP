package ru.nsu.iyaroslavtsev.task113;

import java.util.Map;

class Sub extends Expression {
    private final Expression left, right;
    public Sub(Expression l, Expression r) {
        left = l; right = r;
    }

    @Override public void insert(StringBuilder expr) {
        expr.append('(');
        left.insert(expr);
        expr.append('-');
        right.insert(expr);
        expr.append(')');
    }

    @Override public Expression derivative(String var) {
        return new Sub(left.derivative(var), right.derivative(var));
    }

    @Override public double eval(Map<String, Double> vars) {
        return left.eval(vars) - right.eval(vars);
    }
}