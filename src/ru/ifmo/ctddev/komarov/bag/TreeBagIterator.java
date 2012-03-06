package ru.ifmo.ctddev.komarov.bag;

import java.util.Iterator;
import ru.ifmo.ctddev.komarov.bag.Pair;

/**
* Created by IntelliJ IDEA.
* User: andrey
* Date: 3/6/12
* Time: 9:53 AM
* To change this template use File | Settings | File Templates.
*/
class TreeBagIterator<T extends Comparable<T>> implements Iterator<T> {
    private Iterator<Pair<T>> it;

    public TreeBagIterator(Iterator<Pair<T>> it) {
        this.it = it;
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }

    @Override
    public T next() {
        return it.next().a;
    }

    @Override
    public void remove() {
        it.remove();
    }
}
