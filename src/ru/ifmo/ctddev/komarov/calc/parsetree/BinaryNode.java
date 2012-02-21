package ru.ifmo.ctddev.komarov.calc.parsetree;

import ru.ifmo.ctddev.komarov.calc.exceptions.CalculatorEvaluationException;
import ru.ifmo.ctddev.komarov.calc.Checker;
import ru.ifmo.ctddev.komarov.calc.exceptions.DivisionByZeroException;

public abstract class BinaryNode implements ParseTreeNode {
	private final ParseTreeNode op1;
	private final ParseTreeNode op2;

	public BinaryNode(ParseTreeNode op1, ParseTreeNode op2) {
		this.op1 = op1;
		this.op2 = op2;
	}

	@Override
	public double evaluate(double x) throws CalculatorEvaluationException {
		double result = evaluate(op1.evaluate(x), op2.evaluate(x));
		Checker.checkIfOverflow(result);
		return result;
	}

	protected abstract double evaluate(double op1, double op2) throws DivisionByZeroException;
}
