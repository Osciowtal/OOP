package ru.nsu.iyaroslavtsev.task113;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class.
 */
public abstract class Expression {

    /**
     * print.
     */
    public void print() {
        StringBuilder res = new StringBuilder();
        insert(res);
        System.out.println(res);
    }

    public abstract void insert(StringBuilder x);
    public abstract Expression derivative(String var);
    public abstract double eval(Map<String, Double> vars);
    public double eval(String assigments) {
        return eval(parseArgs(assigments));
    }

    /**
     * Returns map <vars, values></>
     *
     * @param input - string to parse.
     * @return map.
     */
    public Map<String, Double> parseArgs(String input) {

        Map<String, Double> res = new HashMap<>();

        for (String subs : input.split(";")) {
            subs = subs.trim();

            if (subs.isEmpty()) {
                continue;
            }

            String[] arr = subs.split("=");
            String var = arr[0].trim();
            Double val = Double.parseDouble(arr[1].trim());
            res.put(var, val);
        }
        return res;
    }
}
