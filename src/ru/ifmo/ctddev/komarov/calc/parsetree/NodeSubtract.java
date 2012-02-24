package ru.ifmo.ctddev.komarov.calc.parsetree;

/**
* Created by IntelliJ IDEA.
* User: komarov
* Date: 2/17/12
* Time: 2:43 PM
* To change this template use File | Settings | File Templates.
*/
public class NodeSubtract extends BinaryNode {
    public NodeSubtract(ParseTreeNode left, ParseTreeNode right) {
        super(left, right);
    }

    @Override
    protected double merge(double op1, double op2) {
        return op1 - op2;
    }
}
