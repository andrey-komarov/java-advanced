package ru.ifmo.ctddev.komarov.calc;

/**
* Created by IntelliJ IDEA.
* User: komarov
* Date: 2/17/12
* Time: 2:28 PM
* To change this template use File | Settings | File Templates.
*/
public class DivisionByZeroException extends CalculatorEvaluationException {
    public DivisionByZeroException() {
        super();
    }

    public DivisionByZeroException(String s) {
        super(s);
    }

    public DivisionByZeroException(Throwable cause) {
        super(cause);
    }

    public DivisionByZeroException(String s, Throwable cause) {
        super(s, cause);
    }
}
