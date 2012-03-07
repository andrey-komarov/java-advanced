package ru.ifmo.ctddev.komarov.bag;

import java.util.*;

import ru.ifmo.ctddev.komarov.bag.Pair;

/**
* Created by IntelliJ IDEA.
* User: andrey
* Date: 3/6/12
* Time: 9:53 AM
* To change this template use File | Settings | File Templates.
*/
class TreeBagIterator<T extends Comparable<T>> implements Iterator<T> {
    private TreeBag<T> bag;
    private Iterator<Map.Entry<T, List<T>>> it1;
    private Iterator<T> it2;
    private long id;
    private Iterator<T> removeFrom;

    private void check() {
        if (id != bag.id)
            throw new ConcurrentModificationException();
    }

    TreeBagIterator(TreeBag<T> bag, Iterator<Map.Entry<T, List<T>>> it1, long id) {
        this.bag = bag;
        this.it1 = it1;
        this.it2 = it2;
        this.id = id;
        it2 = null;
        removeFrom = null;
    }

    @Override
    public boolean hasNext() {
        if (bag.size() == 0)
            return false;
        return it1.hasNext() || it2.hasNext();
    }

    @Override
    public T next() {
        check();
        if (it1 == null)
            throw new NoSuchElementException();
        if (it2 != null && it2.hasNext()) {
            removeFrom = it2;
            return it2.next();
        }
        while (true) {
            it2 = it1.next().getValue().iterator();
            removeFrom = it2;
            if (it2.hasNext())
                break;
        }
        return it2.next();
    }

    @Override
    public void remove() {
        check();
        if (removeFrom == null) {
            throw new NoSuchElementException();
        }
        removeFrom.remove();
        id++;
        bag.id++;
        bag.size--;
    }
}
