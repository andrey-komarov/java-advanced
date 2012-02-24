package ru.ifmo.ctddev.komarov.calc.parsetree;

/**
* Created by IntelliJ IDEA.
* User: komarov
* Date: 2/17/12
* Time: 2:39 PM
* To change this template use File | Settings | File Templates.
*/
public class NodeY implements ParseTreeNode {
    @Override
    public double evaluate(double x, double y) {
        return y;
    }
}
