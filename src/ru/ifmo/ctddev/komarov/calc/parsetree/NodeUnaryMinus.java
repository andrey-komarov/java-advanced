package ru.ifmo.ctddev.komarov.calc.parsetree;

import ru.ifmo.ctddev.komarov.calc.exceptions.CalculatorEvaluationException;
import ru.ifmo.ctddev.komarov.calc.exceptions.OverflowException;

import static ru.ifmo.ctddev.komarov.calc.Checker.checkIfOverflow;

/**
* Created by IntelliJ IDEA.
* User: komarov
* Date: 2/17/12
* Time: 2:43 PM
* To change this template use File | Settings | File Templates.
*/
public class NodeUnaryMinus implements ParseTreeNode {
    ParseTreeNode next;

    public NodeUnaryMinus(ParseTreeNode next) {
        this.next = next;
    }

    @Override
    public double evaluate(double x) throws CalculatorEvaluationException {
        double res = next.evaluate(x);
        if (checkIfOverflow(-res)) {
            throw new OverflowException("-" + res);
        }
        return -res;
    }
}
