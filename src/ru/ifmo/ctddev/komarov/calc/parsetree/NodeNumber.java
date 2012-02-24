package ru.ifmo.ctddev.komarov.calc.parsetree;

import ru.ifmo.ctddev.komarov.calc.exceptions.OverflowException;

import static ru.ifmo.ctddev.komarov.calc.Checker.checkIfOverflow;

/**
* Created by IntelliJ IDEA.
* User: komarov
* Date: 2/17/12
* Time: 2:40 PM
* To change this template use File | Settings | File Templates.
*/
public class NodeNumber implements ParseTreeNode {
    double value;

    public NodeNumber(double value) {
        this.value = value;
    }

    @Override
    public double evaluate(double x, double y, double z) throws OverflowException {
        checkIfOverflow(value);
        return value;
    }
}
