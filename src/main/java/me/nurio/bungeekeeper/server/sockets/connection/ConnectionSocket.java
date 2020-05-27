package me.nurio.bungeekeeper.server.sockets.connection;

import lombok.Getter;
import lombok.SneakyThrows;
import me.nurio.bungeekeeper.server.sockets.PacketQueue;

import javax.net.ssl.SSLSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ConnectionSocket extends Thread {

    private SSLSocket sslSocket;

    @Getter private String address;

    @Getter private DataInputStream inputStream;
    @Getter private DataOutputStream outputStream;

    @Getter private PacketListener packetListener;
    @Getter private PacketDispenser packetDispenser;
    @Getter private PacketProcessor packetProcessor;

    @Getter private PacketQueue inputQueue = new PacketQueue();
    @Getter private PacketQueue outputQueue = new PacketQueue();

    @SneakyThrows
    public ConnectionSocket(SSLSocket socket) {
        sslSocket = socket;

        address = socket.getInetAddress().getHostAddress();

        System.out.printf("Connecting with '%s'...\n", address);

        inputStream = new DataInputStream(sslSocket.getInputStream());
        outputStream = new DataOutputStream(sslSocket.getOutputStream());

        packetListener = new PacketListener(this);
        packetDispenser = new PacketDispenser(this);
        packetProcessor = new PacketProcessor(this);
    }

    @SneakyThrows
    @Override
    public void run() {
        System.out.printf("Connected with '%s'\n", address);

        packetListener.start();
        packetDispenser.start();
        packetProcessor.start();
    }

}