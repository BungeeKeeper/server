package me.nurio.bungeekeeper.server.sockets.connection;

import lombok.SneakyThrows;
import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.packets.bungee.PostConnectionPacket;
import me.nurio.bungeekeeper.server.events.BungeeConnectEvent;
import me.nurio.bungeekeeper.server.events.PlayerConnectEvent;
import me.nurio.bungeekeeper.server.sockets.PacketQueue;
import me.nurio.events.EventManager;

public class PacketProcessor extends Thread {

    private ConnectionSocket connectionSocket;
    private PacketQueue packetQueue;

    public PacketProcessor(ConnectionSocket connection) {
        connectionSocket = connection;
        packetQueue = connectionSocket.getInputQueue();
    }

    @SneakyThrows
    public void run() {
        while (true) {
            if (!packetQueue.hasPacket()) continue;
            Packet packet = packetQueue.getNextPacket();

            if (packet.getId() == 1) {
                BungeeConnectEvent event = new BungeeConnectEvent();
                event.setServerAddress(connectionSocket.getAddress());
                EventManager.callEvent(event);
            }

            if (packet.getId() == 22) {
                PostConnectionPacket connectionPacket = (PostConnectionPacket) packet;
                PlayerConnectEvent event = new PlayerConnectEvent();
                event.setPlayerName(connectionPacket.getUsername());
                EventManager.callEvent(event);
            }
        }
    }

}