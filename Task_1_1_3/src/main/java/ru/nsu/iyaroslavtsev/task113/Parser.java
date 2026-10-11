package ru.nsu.iyaroslavtsev.task113;

class Parser {
    private final String s;
    private int pos;

    public static Expression parse(String str) {
        Parser p = new Parser(str);
        Expression e = p.parseExpr();
        p.skipSpaces();
        if (p.pos != p.s.length()) {
            throw new IllegalArgumentException(
                    "Trouble at position " + p.pos + ": '" + p.s.substring(p.pos) + "'");
        }
        return e;
    }

    private Parser(String s) {
        this.s = s;
        this.pos = 0;
    }

    private void skipSpaces() {
        while (pos < s.length() && Character.isWhitespace(s.charAt(pos))) pos++;
    }

    // expr = '(' l_expr op r_expr ')' | atom
    private Expression parseExpr() {
        skipSpaces();
        // '(' l_expr op r_expr ')'
        if (pos < s.length() && s.charAt(pos) == '(') {

            pos++;
            Expression l_expr = parseExpr();
            skipSpaces();

            if (pos >= s.length()) throw new IllegalArgumentException("Expected operation");
            char op = s.charAt(pos++);

            Expression r_expr = parseExpr();
            skipSpaces();

            if (pos >= s.length() || s.charAt(pos) != ')')
                throw new IllegalArgumentException("Expected ')' at position " + pos);
            pos++;

            switch (op) {
                case '+':
                    return new Add(l_expr, r_expr);
                case '-':
                    return new Sub(l_expr, r_expr);
                case '*':
                    return new Mul(l_expr, r_expr);
                case '/':
                    return new Div(l_expr, r_expr);
                default:
                    throw new IllegalArgumentException("Unknown operation: " + op);
            }
        }

        // atom: number / variable
        int start = pos;
        while (pos < s.length()) {
            char c = s.charAt(pos);
            if (Character.isLetterOrDigit(c) || c == '_' || c == '.') {
                pos++;
            } else break;
        }
        String tok = s.substring(start, pos);
        if (tok.isEmpty()) {
            throw new IllegalArgumentException("Unexpected symbol at position " + pos + ": '"
                    + (pos < s.length() ? s.charAt(pos) : "EOF") + "'");
        }
        if (tok.matches("\\d+(\\.\\d+)?")) {
            return new Numberr(Double.parseDouble(tok));
        } else {
            Main.variabled = 1;
            return new Variable(tok);
        }
    }
}