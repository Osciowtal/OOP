package ru.nsu.iyaroslavtsev.task113;

/**
 * Parser class.
 */
class Parser {

    private final String stri;
    private int pos;

    /**
     * Parsing.
     *
     * @param str - string.
     * @return expression.
     */
    public static Expression parse(String str) {
        Parser p = new Parser(str);
        Expression e = p.parseExpr();
        p.skipSpaces();
        if (p.pos != p.stri.length()) {
            throw new IllegalArgumentException(
                    "Trouble at position " + p.pos + ": '" + p.stri.substring(p.pos) + "'");
        }
        return e;
    }

    private Parser(String s) {
        this.stri = s;
        this.pos = 0;
    }

    private void skipSpaces() {
        while (pos < stri.length() && Character.isWhitespace(stri.charAt(pos))) {
            pos++;
        }
    }

    // expr = '(' l_expr op r_expr ')' | atom
    private Expression parseExpr() {
        skipSpaces();
        // '(' l_expr op r_expr ')'
        if (pos < stri.length() && stri.charAt(pos) == '(') {

            pos++;
            final Expression leftExpr = parseExpr();
            skipSpaces();

            if (pos >= stri.length()) {
                throw new IllegalArgumentException("Expected operation");
            }
            final char op = stri.charAt(pos++);

            final Expression rightExpr = parseExpr();
            skipSpaces();

            if (pos >= stri.length() || stri.charAt(pos) != ')') {
                throw new IllegalArgumentException("Expected ')' at position " + pos);
            }
            pos++;

            switch (op) {
                case '+':
                    return new Add(leftExpr, rightExpr);
                case '-':
                    return new Sub(leftExpr, rightExpr);
                case '*':
                    return new Mul(leftExpr, rightExpr);
                case '/':
                    return new Div(leftExpr, rightExpr);
                default:
                    throw new IllegalArgumentException("Unknown operation: " + op);
            }
        }

        // atom: number / variable
        int start = pos;
        while (pos < stri.length()) {
            char c = stri.charAt(pos);
            if (Character.isLetterOrDigit(c) || c == '_' || c == '.') {
                pos++;
            } else {
                break;
            }
        }

        String tok = stri.substring(start, pos);
        if (tok.isEmpty()) {
            throw new IllegalArgumentException("Unexpected symbol at position " + pos + ": '"
                    + (pos < stri.length() ? stri.charAt(pos) : "EOF") + "'");
        }
        if (tok.matches("\\d+(\\.\\d+)?")) {
            return new Numberr(Double.parseDouble(tok));
        } else {
            Main.variabled = 1;
            return new Variable(tok);
        }
    }
}