package ru.ifmo.ctddev.komarov.calc;

import ru.ifmo.ctddev.komarov.calc.exceptions.CalculatorEvaluationException;
import ru.ifmo.ctddev.komarov.calc.exceptions.DivisionByZeroException;
import ru.ifmo.ctddev.komarov.calc.exceptions.ParseException;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: andrey
 * Date: 21.02.12
 * Time: 22:14
 * To change this template use File | Settings | File Templates.
 */
public class Function {
    private Calculator calc;

    public Function(String s) throws ParseException {
        init(s);
    }

    public String evaluateWithChecks(double x) {
        try {
            return "" + calc.evaluate(x);
        } catch (DivisionByZeroException e) {
            return "division by zero";
        } catch (CalculatorEvaluationException e) {
            return "overflow";
        }
    }

    public Function(File file) throws IOException, ParseException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String s = br.readLine();
            init(s);
            br.close();
        } catch (IOException e) {
            throw e;
        }
    }

    private void init(String s) throws ParseException {
        calc = new Calculator(s);
    }

    public void write(File file) throws IOException {
        PrintWriter out = new PrintWriter(file);
        write(out);
    }

    public void write(Writer out) throws IOException {
        out.write("x\tf" + System.lineSeparator());
        for (int i = 0; i < 10; i++) {
            out.write(i + "\t");
            out.write(evaluateWithChecks(i) + System.lineSeparator());
        }
    }
}
