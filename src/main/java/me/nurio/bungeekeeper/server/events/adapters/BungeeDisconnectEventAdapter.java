package me.nurio.bungeekeeper.server.events.adapters;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.server.events.EventAdapter;
import me.nurio.bungeekeeper.server.events.types.BungeeDisconnectEvent;
import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;
import me.nurio.events.handler.Event;

public class BungeeDisconnectEventAdapter implements EventAdapter {

    @Override
    public int getPacketId(byte id) {
        return 2;
    }

    @Override
    public Event getEvent(ConnectionSocket socket, Packet packet) {
        BungeeDisconnectEvent event = new BungeeDisconnectEvent();
        event.setServerAddress(socket.getAddress());

        event.setConnectionSocket(socket);
        return event;
    }

}