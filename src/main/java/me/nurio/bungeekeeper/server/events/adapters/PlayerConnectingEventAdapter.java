package me.nurio.bungeekeeper.server.events.adapters;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.packets.bungee.PreConnectionPacket;
import me.nurio.bungeekeeper.server.events.EventAdapter;
import me.nurio.bungeekeeper.server.events.types.PlayerConnectingEvent;
import me.nurio.bungeekeeper.server.management.entities.player.Player;
import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;
import me.nurio.events.handler.Event;

public class PlayerConnectingEventAdapter implements EventAdapter {

    @Override
    public int getPacketId(byte id) {
        return 21;
    }

    @Override
    public Event getEvent(ConnectionSocket socket, Packet packet) {
        PreConnectionPacket connectionPacket = (PreConnectionPacket) packet;

        PlayerConnectingEvent event = new PlayerConnectingEvent();
        event.setEventId(connectionPacket.getEventId());

        Player player = new Player(
            connectionPacket.getUsername(),
            connectionPacket.getProtocol(),
            connectionPacket.getAddress()
        );
        event.setPlayer(player);

        event.setConnectionSocket(socket);
        return event;
    }

}