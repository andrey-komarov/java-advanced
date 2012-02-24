package ru.ifmo.ctddev.komarov.calc;

import ru.ifmo.ctddev.komarov.calc.exceptions.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: andrey
 * Date: 21.02.12
 * Time: 23:15
 * To change this template use File | Settings | File Templates.
 */
public class FileCalc {
    public static void main(String[] args) {
        args = new String[] {"/home/andrey/in"};

        if (args.length != 1) {
            System.out.println("program needs one argument: arithmetic expression");
            return;
        }

        try (
                BufferedReader br = new BufferedReader(new FileReader(new File(args[0])))
        ) {
            ArrayList<Function> functions = new ArrayList<>();
            ArrayList<String> log = new ArrayList<>();
            String s = br.readLine();
            int functionsCnt = 0;
            while (s != null) {
                Function f = null;
                functionsCnt++;
                try {
                    f = new Function(s);
                } catch (ParseException e) {
                    log.add("function #" + functionsCnt + ": parse error: " + e.getMessage());
                } finally {
                    functions.add(f);
                }
                s = br.readLine();
            }
            System.out.print("x\t");
            for (int i = 0; i < functionsCnt; i++) {
                System.out.print("f" + i + "\t");
            }
            System.out.println();
            for (int x = 0; x <= 10; x++) {
                System.out.print(x + "\t");
                for (Function f : functions) {
                    if (f == null) {
                        System.out.print(":(\t");
                    } else {
                        System.out.print(f.evaluateWithChecks(x, x) + "\t");
                    }
                }
                System.out.println();
            }

            for (String legEntry : log) {
                System.out.println(legEntry);
            }

        } catch (IOException e) {
            System.out.println("Error opening file");
        }
    }

}
