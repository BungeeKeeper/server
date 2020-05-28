package me.nurio.bungeekeeper.server.sockets.connection;

import lombok.SneakyThrows;
import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.packets.PacketFactory;
import me.nurio.bungeekeeper.packets.system.GoodbyeSystemPacket;
import me.nurio.bungeekeeper.server.sockets.PacketQueue;

import java.io.DataInputStream;

public class PacketListener extends Thread {

    private ConnectionSocket connectionSocket;

    private PacketQueue packetQueue;
    private DataInputStream inputStream;

    public PacketListener(ConnectionSocket connection) {
        connectionSocket = connection;
        packetQueue = connectionSocket.getInputQueue();
        inputStream = connectionSocket.getInputStream();
    }

    @SneakyThrows
    public void run() {
        while (!isInterrupted()) {
            byte packetId = inputStream.readByte();
            Packet packet = PacketFactory.createPacketById(packetId);
            packet.read(inputStream);
            packetQueue.registerPacket(packet);

            // Stop listener
            if (packetId == GoodbyeSystemPacket.PACKET_ID) break;
        }
    }

}