package ru.ifmo.ctddev.komarov.taskrunner;

public class Main {
    public static void main(String[] args) {
        TaskRunner runner = new TaskRunnerImpl();
        Client[] clients = new Client[20];
        for (int i = 0; i < clients.length; i++)
            clients[i] = new Client(runner, i);
        Thread[] threads = new Thread[clients.length];
        for (int i = 0; i < clients.length; i++) {
            threads[i] = new Thread(clients[i]);
            threads[i].start();
        }
    }
}
