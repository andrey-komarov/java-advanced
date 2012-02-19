package ru.ifmo.ctddev.komarov.calc;

/**
 * Created by IntelliJ IDEA.
 * User: komarov
 * Date: 2/17/12
 * Time: 2:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class Checker {
	final static double MAX_VALUE = 1e10;
	
    public static boolean checkIfOverflow(double a) {
        return a < -MAX_VALUE || a > MAX_VALUE;
    }
}
