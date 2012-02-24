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
                BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
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
            System.out.print("x\ty\tz\t");
            for (int i = 0; i < functionsCnt; i++) {
                System.out.print("f" + i + "\t");
            }
            System.out.println();
            final int maxVal = 3;
            for (int x = 0; x < maxVal; x++) {
                for (int y = 0; y < maxVal; y++) {
                    for (int z = 0; z < maxVal; z++) {
                        System.out.print(x + "\t" + y + "\t" + z + "\t");
                        for (Function f : functions) {
                            if (f == null) {
                                System.out.print(":(\t");
                            } else {
                                System.out.print(f.evaluateWithChecks(x, x, x) + "\t");
                            }
                        }
                        System.out.println();
                    }
                }
            }

            for (String legEntry : log) {
                System.out.println(legEntry);
            }

            if (System.out.checkError()) {
                throw new IOException();
            }
            
        } catch (IOException e) {
            System.out.println("Input/output error");
        }
    }

}
