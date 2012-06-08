package ru.ifmo.ctddev.komarov.taskrunner;

public class Main {
    public static void main(String[] args) {
        TaskRunner runner = new TaskRunnerImpl(10);
        Client[] clients = new Client[10];
        for (int i = 0; i < clients.length; i++)
            clients[i] = new Client(runner, i);
        Thread[] threads = new Thread[clients.length];
        for (int i = 0; i < clients.length; i++) {
            threads[i] = new Thread(clients[i]);
            threads[i].start();
        }
    }
}
