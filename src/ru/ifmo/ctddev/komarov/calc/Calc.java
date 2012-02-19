package ru.ifmo.ctddev.komarov.calc;

public class Calc {
    public static void main(String[] args) {
    	
        args = new String[] {"x * x * x * 1e-3/x - 3"};

        if (args.length != 1) {
            System.out.println("program needs one argument: arithmetic expression");
            return;
        }
        try {
            Calculator calc = new Calculator(args[0]);
            System.out.println("x\tf");
            for (int i = 0; i < 10; i++) {
                System.out.print(i + "\t");
                try {
                    System.out.println(calc.evaluate(i));
                } catch (DivisionByZeroException e) {
                    System.out.println("division by zero");
                } catch (OverflowException e) {
                    System.out.println("overflow");
                } catch (CalculatorEvaluationException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (ParseException e) {
            System.out.println("Parse error: " + e.getMessage());
        }
    }
}