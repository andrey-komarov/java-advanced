package ru.ifmo.ctddev.komarov.calc.parsetree;

/**
* Created by IntelliJ IDEA.
* User: komarov
* Date: 2/17/12
* Time: 2:43 PM
* To change this template use File | Settings | File Templates.
*/
public class NodeProduct extends BinaryNode {
    public NodeProduct(ParseTreeNode left, ParseTreeNode right) {
        super(left, right);
    }

    @Override
    protected double evaluate(double op1, double op2)  {
        return op1 * op2;
    }
}
