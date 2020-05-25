package me.nurio.applicationbase.sockets.connection;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import me.nurio.applicationbase.packets.Packet;
import me.nurio.applicationbase.packets.PacketFactory;
import me.nurio.applicationbase.sockets.PacketQueue;

import java.io.DataInputStream;

public class ConnectionListener extends Thread {

    private ConnectionSocket connectionSocket;

    private PacketQueue packetQueue;
    private DataInputStream inputStream;

    public ConnectionListener(ConnectionSocket connection){
        connectionSocket = connection;
        packetQueue = connectionSocket.getInputQueue();
        inputStream = connectionSocket.getInputStream();
    }

    @SneakyThrows
    public void run() {
        while(true){
            byte packetId = inputStream.readByte();
            Packet packet = PacketFactory.getById(packetId);
            packet.read(inputStream);
            packetQueue.registerPacket(packet);
        }
    }

}