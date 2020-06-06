package me.nurio.bungeekeeper.server.events.adapters;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.packets.bungee.PostConnectionPacket;
import me.nurio.bungeekeeper.server.events.EventAdapter;
import me.nurio.bungeekeeper.server.events.types.PlayerConnectedEvent;
import me.nurio.bungeekeeper.server.management.entities.player.Player;
import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;
import me.nurio.events.handler.Event;

public class PlayerConnectedEventAdapter implements EventAdapter {

    @Override
    public int getPacketId(byte id) {
        return 22;
    }

    @Override
    public Event getEvent(ConnectionSocket socket, Packet packet) {
        PostConnectionPacket connectionPacket = (PostConnectionPacket) packet;

        PlayerConnectedEvent event = new PlayerConnectedEvent();
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