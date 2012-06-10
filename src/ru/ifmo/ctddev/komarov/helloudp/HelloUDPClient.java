package ru.ifmo.ctddev.komarov.helloudp;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

public class HelloUDPClient implements Runnable{

    final static int THREADS = 20;

    private InetAddress address;
    private int port;
    private Thread[] workers;

    private class Spammer implements Runnable {
        int id;

        private Spammer(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            DatagramSocket socket;
            try {
                socket = new DatagramSocket();
            } catch (SocketException e) {
                System.err.println("Error creating socket fot worker #" + id);
                return;
            }
            int n = 0;
            byte[] buf = new byte[256];
            while (!Thread.interrupted()) {
                String message = "Hello #" + n + " to server from #" + id + "!";
                n++;
                byte[] data = message.getBytes();
                Arrays.fill(buf, (byte) 0);
                for (int i = 0; i < data.length; i++)
                    buf[i] = data[i];
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
                try {
                    socket.send(packet);
                    socket.receive(packet);
                    int len = 0;
                    for (; len < buf.length && buf[len] != 0; len++)
                        ;
                    message = new String(packet.getData(), 0, len);
                    System.out.println(message);
                } catch (IOException e) {
                    System.err.println("Error while proceeding '" + message + "'");
                }
            }
        }
    }

    public HelloUDPClient(InetAddress address, int port) throws SocketException {
        this.address = address;
        this.port = port;
    }

    @Override
    public void run() {
        workers = new Thread[THREADS];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Thread(new Spammer(i));
            workers[i].start();
        }
    }

    public static void main(String[] args) throws UnknownHostException, SocketException {
        args = new String[] {"localhost", "12345"};
        if (args.length != 2) {
            System.out.println("Usage: HelloUDPClient <address> <port>");
        }
        InetAddress address = Inet4Address.getByName(args[0]);
        int port = Integer.parseInt(args[1]);
        Thread client = new Thread(new HelloUDPClient(address, port));
        client.start();
    }
}
