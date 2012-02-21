package ru.ifmo.ctddev.komarov.calc.exceptions;

/**
* Created by IntelliJ IDEA.
* User: komarov
* Date: 2/17/12
* Time: 2:28 PM
* To change this template use File | Settings | File Templates.
*/
public class ParseException extends CalculatorException {
    public ParseException() {
        super();
    }

    public ParseException(String s) {
        super(s);
    }

    public ParseException(Throwable cause) {
        super(cause);
    }

    public ParseException(String s, Throwable cause) {
        super(s, cause);
    }
}
