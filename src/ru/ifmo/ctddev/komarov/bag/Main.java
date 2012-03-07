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
        b.add(2);
        b.add(3);
        b.add(4);
        System.err.println(b);
        Iterator<Integer> it = b.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        System.err.println(b);
    }
}
