package me.nurio.applicationbase.sockets.connection;

import lombok.Getter;
import lombok.SneakyThrows;
import me.nurio.applicationbase.packets.Packet;
import me.nurio.applicationbase.sockets.PacketQueue;

import javax.net.ssl.SSLSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ConnectionSocket extends Thread {

    private SSLSocket sslSocket;

    @Getter private String address;

    @Getter private DataInputStream inputStream;
    @Getter private DataOutputStream outputStream;

    @Getter private ConnectionListener connectionListener;
    @Getter private ConnectionAttender connectionAttender;

    @Getter private PacketQueue inputQueue = new PacketQueue();
    @Getter private PacketQueue outputQueue = new PacketQueue();

    @SneakyThrows
    public ConnectionSocket(SSLSocket socket) {
        sslSocket = socket;

        address = socket.getInetAddress().getHostAddress();

        System.out.printf("Connecting with '%s'...\n", address);

        inputStream = new DataInputStream(sslSocket.getInputStream());
        outputStream = new DataOutputStream(sslSocket.getOutputStream());
        connectionListener = new ConnectionListener(this);
        connectionAttender = new ConnectionAttender(this);
    }

    @SneakyThrows
    @Override
    public void run() {
        System.out.printf("Connected with '%s'\n", address);

        connectionListener.start();
        connectionAttender.start();
    }
}