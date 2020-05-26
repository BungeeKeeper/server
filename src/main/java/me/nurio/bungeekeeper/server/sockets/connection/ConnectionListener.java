package me.nurio.bungeekeeper.server.sockets.connection;

import lombok.SneakyThrows;
import me.nurio.bungeekeeper.server.sockets.PacketQueue;
import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.packets.PacketFactory;

import java.io.DataInputStream;

public class ConnectionListener extends Thread {

    private ConnectionSocket connectionSocket;

    private PacketQueue packetQueue;
    private DataInputStream inputStream;

    public ConnectionListener(ConnectionSocket connection) {
        connectionSocket = connection;
        packetQueue = connectionSocket.getInputQueue();
        inputStream = connectionSocket.getInputStream();
    }

    @SneakyThrows
    public void run() {
        while (true) {
            byte packetId = inputStream.readByte();
            Packet packet = PacketFactory.createPacketById(packetId);
            packet.read(inputStream);
            System.out.println(packetQueue.toString());
            packetQueue.registerPacket(packet);
        }
    }

}