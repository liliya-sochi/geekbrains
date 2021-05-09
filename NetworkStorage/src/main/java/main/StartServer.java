package main;

import server.Server;

public class StartServer {
    public static void main(String[] args) throws InterruptedException {
        new Server().start();
    }
}
