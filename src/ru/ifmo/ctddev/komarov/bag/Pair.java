package ru.ifmo.ctddev.komarov.bag;

/**
* Created by IntelliJ IDEA.
* User: andrey
* Date: 3/6/12
* Time: 9:42 AM
* To change this template use File | Settings | File Templates.
*/

class Pair<T extends Comparable<T>> implements Comparable<Pair<T>>{
    T a;
    long id;

    Pair(T a, long id) {
        this.a = a;
        this.id = id;
    }

    @Override
    public int compareTo(Pair<T> o) {
        int res = a.compareTo(o.a);
        if (res != 0)
            return res;
        return Long.compare(id, o.id);
    }
}
