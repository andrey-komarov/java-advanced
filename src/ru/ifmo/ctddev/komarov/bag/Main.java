package ru.ifmo.ctddev.komarov.bag;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: andrey
 * Date: 3/6/12
 * Time: 9:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        LinkedBag<Integer> b = new LinkedBag<>();
        for (int i = 0; i < 10; i++) {
            b.add (17 * i % 3);
        }
        for (int i = 2; i < 9; i++)
            b.remove(17 * i % 3);
        for (int i : b) {
            System.err.print(i);
        }
    }
}
