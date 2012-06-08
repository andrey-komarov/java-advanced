package ru.ifmo.ctddev.komarov.taskrunner;

public interface TaskRunner {
    <X, Y> X run(Task<X, Y> task, Y value) throws InterruptedException;

    void stop();
}
