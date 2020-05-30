package me.nurio.bungeekeeper.server.events.adapters;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.packets.bungee.ConnectionPacket;
import me.nurio.bungeekeeper.server.events.EventAdapter;
import me.nurio.bungeekeeper.server.events.types.PlayerConnectingEvent;
import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;
import me.nurio.events.handler.Event;

import java.util.UUID;

public class PlayerConnectingEventAdapter implements EventAdapter {

    @Override
    public int getPacketId(byte id) {
        return 21;
    }

    @Override
    public Event getEvent(ConnectionSocket socket, Packet packet) {
        ConnectionPacket connectionPacket = (ConnectionPacket) packet;

        PlayerConnectingEvent event = new PlayerConnectingEvent();
        event.setEventId(connectionPacket.getEventId());
        event.setAddress(connectionPacket.getAddress());
        event.setPlayerName(connectionPacket.getUsername());
        event.setUniqueId(UUID.randomUUID());

        event.setConnectionSocket(socket);
        return event;
    }

}