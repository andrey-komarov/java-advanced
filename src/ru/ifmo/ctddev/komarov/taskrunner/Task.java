package ru.ifmo.ctddev.komarov.taskrunner;

public interface Task<X, Y> {
    X run(Y value);
}
