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
	public double evaluate(double x, double y, double z) throws CalculatorEvaluationException {
		double result = merge(op1.evaluate(x, y, z), op2.evaluate(x, y, z));
		Checker.checkIfOverflow(result);
		return result;
	}

	protected abstract double merge(double op1, double op2) throws DivisionByZeroException;
}
