package ru.ifmo.ctddev.komarov.taskrunner;

import java.util.ArrayDeque;
import java.util.Queue;

public class TaskRunnerImpl implements TaskRunner {

    private static class TaskWrapper<X, Y> {
        Task<X, Y> task;
        Y value;
        X result;
        boolean ready;

        TaskWrapper(Task<X, Y> task, Y value) {
            this.task = task;
            this.value = value;
        }
    }

    private class Executor implements Runnable {
        @Override
        public void run() {
            while (!Thread.interrupted()) {
                TaskWrapper now;
                synchronized (queue) {
                    try {
                        while (queue.isEmpty())
                            queue.wait();
                    } catch (InterruptedException e) {

                    }
                    now = queue.poll();
                }
                synchronized (now) {
                    now.result = now.task.run(now.value);
                    now.ready = true;
                    now.notifyAll();
                }
            }
        }
    }

    private Queue<TaskWrapper> queue;
    Thread[] workers;

    public TaskRunnerImpl(int n) {
        queue = new ArrayDeque<>();
        workers = new Thread[n];
        for (int i = 0; i < n; i++) {
            workers[i] = new Thread(new Executor());
            workers[i].start();
        }
    }


    @Override
    public void stop() {
        for (Thread worker : workers) {
            worker.interrupt();
        }
    }

    @Override
    public <X, Y> X run(Task<X, Y> task, Y value) throws InterruptedException {
        TaskWrapper<X, Y> here = new TaskWrapper<X, Y>(task, value);
        synchronized (queue) {
            queue.add(here);
            queue.notify();
        }
        synchronized (here) {
            while (!here.ready)   {
                here.wait();
            }
        }
        return here.result;
    }
}
