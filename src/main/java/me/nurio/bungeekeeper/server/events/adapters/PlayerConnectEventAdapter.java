package me.nurio.bungeekeeper.server.events.adapters;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.packets.bungee.ConnectionPacket;
import me.nurio.bungeekeeper.server.events.EventAdapter;
import me.nurio.bungeekeeper.server.events.types.PlayerConnectEvent;
import me.nurio.bungeekeeper.server.management.entities.player.Player;
import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;
import me.nurio.events.handler.Event;

public class PlayerConnectEventAdapter implements EventAdapter {

    @Override
    public int getPacketId(byte id) {
        return 25;
    }

    @Override
    public Event getEvent(ConnectionSocket socket, Packet packet) {
        ConnectionPacket connectionPacket = (ConnectionPacket) packet;

        PlayerConnectEvent event = new PlayerConnectEvent();
        event.setEventId(connectionPacket.getEventId());

        Player player = new Player(
            connectionPacket.getUsername(),
            connectionPacket.getUniqueId(),
            connectionPacket.getProtocol(),
            connectionPacket.isPremium(),
            connectionPacket.getAddress()
        );
        event.setPlayer(player);

        event.setConnectionSocket(socket);
        return event;
    }

}