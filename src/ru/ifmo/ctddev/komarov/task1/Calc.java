package ru.ifmo.ctddev.komarov.task1;

import ru.ifmo.ctddev.komarov.task1.Calculator;

public class Calc {
	public static void main(String[] args) {
//		args = new String[] {"-5 * x + x * x"};
		if (args.length != 1) {
			System.out.println("program needs one argument: arithmetic expression");
		}
		try {
			Calculator calc = new Calculator(args[0]);
			System.out.println("x\tf");
			for (int i = 0; i < 10; i++) {
				System.out.print(i + "\t");
				try {
					System.out.println(calc.evaluate(i));
				} catch (Calculator.DivisionByZeroException e) {
					System.out.println("division by zero");
				} catch (Calculator.OverflowException e) {
					System.out.println("overflow");
				}
			}
		} catch (Calculator.ParseException e) {
			System.out.println("Parse error: " + e.getMessage());
		}
	}
}
