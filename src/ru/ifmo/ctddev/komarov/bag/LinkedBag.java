package ru.ifmo.ctddev.komarov.bag;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: andrey
 * Date: 3/6/12
 * Time: 10:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class LinkedBag<T> extends AbstractCollection<T> implements Bag<T> {

    long time;
    HashMap<Long, T> order;
    HashMap<T, TreeSet<Long>> values;

    private class LinkedBagIterator implements Iterator<T> {
        private Iterator<Map.Entry<Long, T>> it;
        long last;

        public LinkedBagIterator(Iterator<Map.Entry<Long, T>> it) {
            this.it = it;
            last = -1;
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public T next() {
            Map.Entry<Long, T> e = it.next();
            last = e.getKey();
            return e.getValue();
        }

        @Override
        public void remove() {
            it.remove();
            T toRemove = order.get(last);
            TreeSet<Long> where = values.get(toRemove);
            where.remove(last);
            if (where.size() == 0) {
                values.remove(toRemove);
            }
        }
    }

    LinkedBag() {
        time = 0;
        order = new HashMap<>();
        values = new HashMap<>();
    }

    @Override
    public int size() {
        return order.size();
    }

    @Override
    public boolean isEmpty() {
        return order.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        T t = (T) o;
        return values.containsKey(t);
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedBagIterator(order.entrySet().iterator());
    }

    @Override
    public boolean add(T t) {
        order.put(time, t);
        if (!values.containsKey(t)) {
            values.put(t, new TreeSet<Long>());
        }
        values.get(t).add(time);
        time++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (!values.containsKey(o)) {
            return false;
        }
        TreeSet<Long> removeFrom = values.get(o);
        Iterator<Long> removeIt = removeFrom.iterator();
        Long removed = removeIt.next();
        removeIt.remove();
        if (removeFrom.size() == 0) {
            values.remove(o);
        }
        order.remove(removed);
        return true;
    }

    @Override
    public void clear() {
        values.clear();
        order.clear();
    }
}