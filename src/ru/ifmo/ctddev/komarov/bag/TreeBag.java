package ru.ifmo.ctddev.komarov.bag;

import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.AbstractCollection;

public class TreeBag<T extends Comparable<T>> extends AbstractCollection<T> implements Bag<T> {

    long id;
    SortedSet<Pair<T>> values;

    public TreeBag () {
        values = new TreeSet<Pair<T>>();
        id = 0;
    }

    private Pair<T> getAny(T val) {
        Pair<T> tmp = values.tailSet(new Pair<T>(val, 0)).first();
        return tmp;
    }
    
    @Override
    public int size() {
        return values.size();
    }

    @Override
    public boolean isEmpty() {
        return values.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        T t = (T) o;
        return t.equals(getAny(t).a);
    }

    @Override
    public Iterator<T> iterator() {
        return new TreeBagIterator<T>(values.iterator());
    }

    @Override
    public boolean add(T t) {
        return values.add(new Pair<T>(t, id++));
    }

    @Override
    public boolean remove(Object o) {
        T t = (T) o;
        Pair<T> tmp = getAny(t);
        if (tmp.a.equals(o)) {
            return values.remove(tmp);
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        values.clear();
    }
}
