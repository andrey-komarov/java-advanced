package ru.ifmo.ctddev.komarov.bag;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: andrey
 * Date: 3/6/12
 * Time: 9:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        Bag<Integer> b = new TreeBag<>();
        for (int i = 0; i < 10; i++) {
            b.add(i);
            System.err.println(b);
        }
        for (int i = 0; i < 10; i++) {
            b.remove(i);
              System.err.println(b);
        }
    }
}
