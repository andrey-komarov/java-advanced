package ru.ifmo.ctddev.komarov.taskrunner;

import java.util.ArrayDeque;
import java.util.Queue;

public class TaskRunnerImpl implements TaskRunner {
    Queue<Task> tasks;

    public TaskRunnerImpl() {
        tasks = new ArrayDeque<>();
    }

    @Override
    public <X, Y> X run(Task<X, Y> task, Y value) {

    }
}
