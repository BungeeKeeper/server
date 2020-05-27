package me.nurio.bungeekeeper.server.sockets.connection;

import lombok.SneakyThrows;
import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.server.sockets.PacketQueue;

import java.io.DataOutputStream;

public class PacketDispenser extends Thread {

    private ConnectionSocket connectionSocket;

    private PacketQueue packetQueue;
    private DataOutputStream outputStream;

    public PacketDispenser(ConnectionSocket connection) {
        connectionSocket = connection;
        packetQueue = connectionSocket.getOutputQueue();
        outputStream = connectionSocket.getOutputStream();
    }

    @SneakyThrows
    public void run() {
        while (true) {
            if (!packetQueue.hasPacket()) continue;

            Packet packet = packetQueue.getNextPacket();
            packet.write(outputStream);
        }
    }

}