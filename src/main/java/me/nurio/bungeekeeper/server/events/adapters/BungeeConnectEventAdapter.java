package me.nurio.bungeekeeper.server.events.adapters;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.server.events.EventAdapter;
import me.nurio.bungeekeeper.server.events.types.BungeeConnectEvent;
import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;
import me.nurio.events.handler.Event;

public class BungeeConnectEventAdapter implements EventAdapter {

    @Override
    public int getPacketId(byte id) {
        return 1;
    }

    @Override
    public Event getEvent(ConnectionSocket socket, Packet packet) {
        BungeeConnectEvent event = new BungeeConnectEvent();
        event.setServerAddress(socket.getAddress());

        event.setConnectionSocket(socket);
        return event;
    }

}