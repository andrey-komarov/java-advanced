package ru.ifmo.ctddev.komarov.bag;

import java.util.*;

public class TreeBag<T extends Comparable<T>> extends AbstractCollection<T> implements Bag<T> {

    long id;
    TreeMap<T, TreeSet<T>> values;
    int size;
    
    public TreeBag () {
        values = new TreeMap<>();
        id = 0;
        size = 0;
    }

    public TreeBag (Collection<T> items) {
        this();
        addAll(items);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return values.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return values.containsKey(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new TreeBagIterator<T>(this, values.entrySet().iterator(), id);
    }

    @Override
    public boolean add(T t) {
        size++;
        id++;
        if (!values.containsKey(t)) {
            values.put(t, new TreeSet<T>());
        }
        values.get(t).add(t);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (!values.containsKey(o))
            return false;
        Set<T> removeFrom = values.get(o);
        T elem = removeFrom.iterator().next();
        id++;
        size--;
        removeFrom.remove(elem);
        if (removeFrom.size() == 0) {
            values.remove(o);
        }
        return true;
    }

    @Override
    public void clear() {
        values.clear();
        size = 0;
    }
}
