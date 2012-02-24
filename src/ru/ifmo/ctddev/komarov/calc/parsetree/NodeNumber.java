package ru.ifmo.ctddev.komarov.calc.parsetree;

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
    public double evaluate(double x, double y, double z) {
        return value;
    }
}
