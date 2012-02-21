package ru.ifmo.ctddev.komarov.calc;

import ru.ifmo.ctddev.komarov.calc.exceptions.CalculatorEvaluationException;
import ru.ifmo.ctddev.komarov.calc.exceptions.ParseException;
import ru.ifmo.ctddev.komarov.calc.parsetree.*;

public class Calculator {
    private final String expression;
    private ParseTreeNode tree;
    private int pos;

    private void advance() throws ParseException {
        pos++;
        if (pos == expression.length() + 1) {
            throw new ParseException("unexpected end of line");
        }
    }

    private char charAt(int i) throws ParseException {
        if (pos >= expression.length()) {
            throw new ParseException("unxepected end of line");
        }
        return expression.charAt(i);
    }

    public Calculator(String expression) throws ParseException {
        StringBuilder sb = new StringBuilder();
        for (char ch : expression.toCharArray()) {
            if (ch != ' ') {
                sb.append(ch);
            }
        }
        this.expression = sb.toString();
        pos = 0;
        tree = parseSum();
        if (pos != this.expression.length()) {
            throw new ParseException("something in the end of string");
        }
    }

    public double evaluate(double x) throws CalculatorEvaluationException {
        return tree.evaluate(x);
    }

    private ParseTreeNode parseExpression() throws ParseException {
        if (charAt(pos) == '-') {
            advance();
            return new NodeUnaryMinus(parseExpression());
        } else if (charAt(pos) == '+') {
            advance();
            return new NodeUnaryPlus(parseExpression());
        } else if (charAt(pos) == '(') {
            advance();
            ParseTreeNode result = parseSum();
            if (charAt(pos) != ')') {
                throw new ParseException("Skipped ) at position " + pos);
            }
            advance();
            return result;
        } else if (charAt(pos) == 'x' || charAt(pos) == 'X') {
            advance();
            return new NodeX();
        } else {
            return parseNumber();
        }
    }

    private ParseTreeNode parseNumber() throws ParseException {
        StringBuilder sb = new StringBuilder();
        while (pos < expression.length() && Character.isDigit(charAt(pos))) {
            sb.append(charAt(pos));
            advance();
        }
        if (pos < expression.length() && charAt(pos) == '.') {
            sb.append(charAt(pos));
            advance();
            while (pos < expression.length() && Character.isDigit(charAt(pos))) {
                sb.append(charAt(pos));
                advance();
            }
        }
        if (pos < expression.length() && (charAt(pos) == 'e' || charAt(pos) == 'E')) {
            sb.append(charAt(pos));
            advance();
            if (charAt(pos) == '+' || charAt(pos) == '-') {
                sb.append(charAt(pos));
                advance();
            }
            while (pos < expression.length() && Character.isDigit(charAt(pos))) {
                sb.append(charAt(pos));
                advance();
            }
        }
        try {
            double value = Double.parseDouble(sb.toString());
            return new NodeNumber(value);
        } catch (NumberFormatException e) {
            throw new ParseException("Integer expected, \"" + sb.toString() + "\" found");
        }
    }

    private ParseTreeNode parseSum() throws ParseException {
        ParseTreeNode result = parseProduct();
        while (true) {
            if (pos == expression.length()) {
                return result;
            } else if (charAt(pos) == '+') {
                advance();
                ParseTreeNode now = parseProduct();
                result = new NodeAdd(result, now);
            } else if (charAt(pos) == '-') {
                advance();
                ParseTreeNode now = parseProduct();
                result = new NodeSubtract(result, now);
            } else {
                return result;
            }
        }
    }

    private boolean expect(final char op) throws ParseException {
        if (pos == expression.length()) {
            return false;
        }
        if (charAt(pos) == op) {
            advance();
            return true;
        }
        return false;
    }


    private ParseTreeNode parseProduct() throws ParseException {
        ParseTreeNode result = parseExpression();
        while (true) {
            if (expect('*')) {
                result = new NodeProduct(result, parseExpression());
            } else if (expect('/')) {
                result = new NodeDivide(result, parseExpression());
            } else {
                return result;
            }
        }
    }
}