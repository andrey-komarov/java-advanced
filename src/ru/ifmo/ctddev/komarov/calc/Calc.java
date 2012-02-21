package ru.ifmo.ctddev.komarov.calc;

import ru.ifmo.ctddev.komarov.calc.exceptions.ParseException;

import java.io.IOException;
import java.io.PrintWriter;

public class Calc {
    public static void main(String[] args) {
        args = new String[]{"x * x * x * 1e-3/x - 3"};

        if (args.length != 1) {
            System.out.println("program needs one argument: arithmetic expression");
            return;
        }

        try (
                PrintWriter out = new PrintWriter(System.out);
        ) {
            Function f = new Function(args[0]);
            f.write(out);
        } catch (ParseException e) {
            System.out.println("Parse error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}