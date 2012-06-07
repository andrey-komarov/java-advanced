package ru.ifmo.ctddev.komarov.taskrunner;

public class Client implements Runnable {

    TaskRunner runner;
    int id;

    public Client(TaskRunner runner, int id) {
        this.runner = runner;
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; ; i++) {
            Task<String, Integer> t = new SimpleTask();
            String s = runner.run(t, i);
            System.out.println("Hello from client #" + id + "! Result = " + s);
        }
    }
}
