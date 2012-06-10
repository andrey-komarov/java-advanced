package ru.ifmo.ctddev.komarov.helloudp;

import java.net.SocketException;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws SocketException, UnknownHostException {
//        Random rnd = new Random();
        int port = 44444;
        System.out.println("Using port " + port);
        Thread server = new Thread(new HelloUDPServer(port));
        new Thread(new HelloUDPServer(port)).start();

    }
}
