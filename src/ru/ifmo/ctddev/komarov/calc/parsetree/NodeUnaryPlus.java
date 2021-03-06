package ru.ifmo.ctddev.komarov.calc.parsetree;

import ru.ifmo.ctddev.komarov.calc.exceptions.CalculatorEvaluationException;

/**
* Created by IntelliJ IDEA.
* User: komarov
* Date: 2/17/12
* Time: 2:39 PM
* To change this template use File | Settings | File Templates.
*/
public class NodeUnaryPlus implements ParseTreeNode {
    ParseTreeNode next;

    public NodeUnaryPlus(ParseTreeNode next) {
        this.next = next;
    }

    @Override
    public double evaluate(double x, double y, double z) throws CalculatorEvaluationException {
        return next.evaluate(x, y, z);
    }
}
