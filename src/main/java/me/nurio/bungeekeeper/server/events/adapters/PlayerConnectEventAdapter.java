package me.nurio.bungeekeeper.server.events.adapters;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.packets.bungee.PostConnectionPacket;
import me.nurio.bungeekeeper.server.events.EventAdapter;
import me.nurio.bungeekeeper.server.events.types.PlayerConnectEvent;
import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;
import me.nurio.events.handler.Event;

public class PlayerConnectEventAdapter implements EventAdapter {

    @Override
    public int getPacketId(byte id) {
        return 22;
    }

    @Override
    public Event getEvent(ConnectionSocket socket, Packet packet) {
        PostConnectionPacket connectionPacket = (PostConnectionPacket) packet;

        PlayerConnectEvent event = new PlayerConnectEvent();
        event.setAddress(connectionPacket.getAddress());
        event.setPlayerName(connectionPacket.getUsername());
        event.setUniqueId(connectionPacket.getUniqueId());

        event.setConnectionSocket(socket);
        return event;
    }

}