package ru.ifmo.ctddev.komarov.calc.exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: andrey
 * Date: 19.02.12
 * Time: 20:32
 * To change this template use File | Settings | File Templates.
 */
public abstract class CalculatorEvaluationException extends CalculatorException{
    public CalculatorEvaluationException() {
        super();
    }

    public CalculatorEvaluationException(String s) {
        super(s);
    }

    public  CalculatorEvaluationException(Throwable cause) {
        super(cause);
    }

    public CalculatorEvaluationException(String s, Throwable cause) {
        super(s, cause);
    }
}
