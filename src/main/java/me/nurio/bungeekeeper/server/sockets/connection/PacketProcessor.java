package me.nurio.bungeekeeper.server.sockets.connection;

import lombok.SneakyThrows;
import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.server.events.EventAdapterManager;
import me.nurio.bungeekeeper.server.sockets.PacketQueue;
import me.nurio.events.EventManager;
import me.nurio.events.handler.Event;

import java.util.concurrent.CompletableFuture;

public class PacketProcessor extends Thread {

    private ConnectionSocket connectionSocket;
    private PacketQueue packetQueue;

    public PacketProcessor(ConnectionSocket connection) {
        connectionSocket = connection;
        packetQueue = connectionSocket.getInputQueue();
    }

    @SneakyThrows
    public void run() {
        while (!isInterrupted()) {
            if (!packetQueue.hasPacket()) continue;
            Packet packet = packetQueue.getNextPacket();
            CompletableFuture.runAsync(() -> {
                Event event = EventAdapterManager.getEvent(connectionSocket, packet);
                EventManager.callEvent(event);
            });
        }
    }

}