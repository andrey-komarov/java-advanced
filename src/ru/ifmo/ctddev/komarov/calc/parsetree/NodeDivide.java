package ru.ifmo.ctddev.komarov.calc.parsetree;

import ru.ifmo.ctddev.komarov.calc.exceptions.DivisionByZeroException;

/**
* Created by IntelliJ IDEA.
* User: komarov
* Date: 2/17/12
* Time: 2:43 PM
* To change this template use File | Settings | File Templates.
*/
public class NodeDivide extends BinaryNode {
    public NodeDivide(ParseTreeNode left, ParseTreeNode right) {
        super(left, right);
    }

    @Override
    protected double evaluate(double op1, double op2) throws DivisionByZeroException {
        if (op2 == 0) {
            throw new DivisionByZeroException();
        }
        return op1 / op2;
    }
}
