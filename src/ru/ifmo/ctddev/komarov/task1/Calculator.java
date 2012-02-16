package ru.ifmo.ctddev.komarov.task1;

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

	public int evaluate(int x) throws OverflowException,
			DivisionByZeroException {
		return tree.evaluate(x);
	}

	public class CalculatorException extends Exception {
		public CalculatorException() {
			super();
		}

		public CalculatorException(String s) {
			super(s);
		}

		public CalculatorException(Throwable cause) {
			super(cause);
		}

		public CalculatorException(String s, Throwable cause) {
			super(s, cause);
		}
	}

	public class ParseException extends CalculatorException {
		public ParseException() {
			super();
		}

		public ParseException(String s) {
			super(s);
		}

		public ParseException(Throwable cause) {
			super(cause);
		}

		public ParseException(String s, Throwable cause) {
			super(s, cause);
		}
	}

	public class DivisionByZeroException extends CalculatorException {
		public DivisionByZeroException() {
			super();
		}

		public DivisionByZeroException(String s) {
			super(s);
		}

		public DivisionByZeroException(Throwable cause) {
			super(cause);
		}

		public DivisionByZeroException(String s, Throwable cause) {
			super(s, cause);
		}
	}

	public class OverflowException extends CalculatorException {
		public OverflowException() {
			super();
		}

		public OverflowException(String s) {
			super(s);
		}

		public OverflowException(Throwable cause) {
			super(cause);
		}

		public OverflowException(String s, Throwable cause) {
			super(s, cause);
		}
	}

	private interface ParseTreeNode {
		int evaluate(int x) throws OverflowException, DivisionByZeroException;
	}

	private class NodeUnaryMinus implements ParseTreeNode {
		ParseTreeNode next;

		public NodeUnaryMinus(ParseTreeNode next) {
			this.next = next;
		}

		@Override
		public int evaluate(int x) throws OverflowException,
				DivisionByZeroException {
			int res = next.evaluate(x);
			if (-(long) res != -res) {
				throw new OverflowException("-" + res);
			}
			return -res;
		}
	}

	private class NodeUnaryPlus implements ParseTreeNode {
		ParseTreeNode next;

		public NodeUnaryPlus(ParseTreeNode next) {
			this.next = next;
		}

		@Override
		public int evaluate(int x) throws OverflowException,
				DivisionByZeroException {
			return next.evaluate(x);
		}
	}

	private class NodeX implements ParseTreeNode {
		@Override
		public int evaluate(int x) {
			return x;
		}
	}

	private class NodeNumber implements ParseTreeNode {
		int value;

		public NodeNumber(int value) {
			this.value = value;
		}

		@Override
		public int evaluate(int x) {
			return value;
		}
	}

	private class NodeAdd implements ParseTreeNode {
		ParseTreeNode left, right;

		public NodeAdd(ParseTreeNode left, ParseTreeNode right) {
			this.left = left;
			this.right = right;
		}

		@Override
		public int evaluate(int x) throws OverflowException,
				DivisionByZeroException {
			int first = left.evaluate(x);
			int second = right.evaluate(x);
			if (first + (long) second != first + second) {
				throw new OverflowException(first + " + " + second);
			}
			return left.evaluate(x) + right.evaluate(x);
		}
	}

	private class NodeSubtract implements ParseTreeNode {
		ParseTreeNode left, right;

		public NodeSubtract(ParseTreeNode left, ParseTreeNode right) {
			this.left = left;
			this.right = right;
		}

		@Override
		public int evaluate(int x) throws OverflowException,
				DivisionByZeroException {
			int first = left.evaluate(x);
			int second = right.evaluate(x);
			if (first + (long) second != first + second) {
				throw new OverflowException(first + " + " + second);
			}
			return first - second;
		}
	}

	private class NodeProduct implements ParseTreeNode {
		ParseTreeNode left, right;

		public NodeProduct(ParseTreeNode left, ParseTreeNode right) {
			this.left = left;
			this.right = right;
		}

		@Override
		public int evaluate(int x) throws OverflowException,
				DivisionByZeroException {
			int first = left.evaluate(x);
			int second = right.evaluate(x);
			if (first * (long) second != first * second) {
				throw new OverflowException(first + " + " + second);
			}
			return first * second;
		}
	}

	private class NodeDivide implements ParseTreeNode {
		ParseTreeNode left, right;

		public NodeDivide(ParseTreeNode left, ParseTreeNode right) {
			this.left = left;
			this.right = right;
		}

		@Override
		public int evaluate(int x) throws OverflowException,
				DivisionByZeroException {
			int q = right.evaluate(x);
			if (q == 0) {
				throw new DivisionByZeroException();
			}
			int p = left.evaluate(x);
			return p / q;
		}
	}

	private ParseTreeNode parseExpression() throws ParseException {
		if (expression.charAt(pos) == '-') {
			advance();
			return new NodeUnaryMinus(parseExpression());
		} else if (expression.charAt(pos) == '+') {
			advance();
			return new NodeUnaryPlus(parseExpression());
		} else if (expression.charAt(pos) == '(') {
			advance();
			ParseTreeNode result = parseSum();
			if (expression.charAt(pos) != ')') {
				throw new ParseException("Skipped ) at position " + pos);
			}
			advance();
			return result;
		} else if (expression.charAt(pos) == 'x'
				|| expression.charAt(pos) == 'X') {
			advance();
			return new NodeX();
		} else {
			return parseNumber();
		}
	}

	private ParseTreeNode parseNumber() throws ParseException {
		StringBuilder sb = new StringBuilder();
		while (pos < expression.length()
				&& Character.isDigit(expression.charAt(pos))) {
			sb.append(expression.charAt(pos));
			advance();
		}
		try {
			int value = Integer.parseInt(sb.toString());
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
			} else if (expression.charAt(pos) == '+') {
				advance();
				ParseTreeNode now = parseProduct();
				result = new NodeAdd(result, now);
			} else if (expression.charAt(pos) == '-') {
				advance();
				ParseTreeNode now = parseProduct();
				result = new NodeSubtract(result, now);
			} else {
				return result;
			}
		}
	}

	private ParseTreeNode parseProduct() throws ParseException {
		ParseTreeNode result = parseExpression();
		while (true) {
			if (pos == expression.length()) {
				return result;
			} else if (expression.charAt(pos) == '*') {
				advance();
				result = new NodeProduct(result, parseExpression());
			} else if (expression.charAt(pos) == '/') {
				advance();
				result = new NodeDivide(result, parseExpression());
			} else {
				return result;
			}
		}
	}
}
