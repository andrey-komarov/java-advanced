package ru.ifmo.ctddev.komarov.calc.exceptions;

/**
* Created by IntelliJ IDEA.
* User: komarov
* Date: 2/17/12
* Time: 2:27 PM
* To change this template use File | Settings | File Templates.
*/
public class CalculatorException extends Exception {
    public CalculatorException() {
        super();
    }

    public CalculatorException(String s) {
        super(s);
    }

    public CalculatorException(Throwable cause) {
        super(cause);
    }

    public CalculatorException(String s, Throwable cause) {
        super(s, cause);
    }
}
