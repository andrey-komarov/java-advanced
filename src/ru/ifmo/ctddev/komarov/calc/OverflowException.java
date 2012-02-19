package ru.ifmo.ctddev.komarov.calc;

/**
* Created by IntelliJ IDEA.
* User: komarov
* Date: 2/17/12
* Time: 2:28 PM
* To change this template use File | Settings | File Templates.
*/
public class OverflowException extends CalculatorEvaluationException {
    public OverflowException() {
        super();
    }

    public OverflowException(String s) {
        super(s);
    }

    public OverflowException(Throwable cause) {
        super(cause);
    }

    public OverflowException(String s, Throwable cause) {
        super(s, cause);
    }
}
