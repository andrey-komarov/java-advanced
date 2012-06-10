package ru.ifmo.ctddev.komarov.helloudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HelloUDPServer implements Runnable {

    private final int THREADS = 10;

    private DatagramSocket socket;
    private int port;
    private Executor executor;

    public HelloUDPServer(int port) throws SocketException {
        this.port = port;
        socket = new DatagramSocket(port);
        executor = Executors.newFixedThreadPool(THREADS);
    }

    private class Task implements Runnable {
        DatagramPacket input;

        private Task(DatagramPacket input) {
            this.input = input;
        }

        @Override
        public void run() {
            String data = new String(input.getData(), 0, input.getLength());
            System.out.println("Processing " + data + "...");
            String result = "Hello from server! > " + data;
            System.err.println(result);
            byte[] buf = result.getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, input.getAddress(), input.getPort());
            try {
                socket.send(packet);
            } catch (IOException e) {
                System.out.println("Failed to proceed packet: " + result);
            }
        }
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                executor.execute(new Task(packet));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws SocketException {
        if (args.length != 1) {
            System.err.println("Usage: HelloUDPServer <port>");
            return;
        }
        int port = Integer.parseInt(args[0]);
        Thread server = new Thread(new HelloUDPServer(port));
        server.start();
    }
}
