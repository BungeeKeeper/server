package me.nurio.bungeekeeper.server.events;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;
import me.nurio.events.handler.Event;

public interface EventAdapter {

    int getPacketId(byte id);

    Event getEvent(ConnectionSocket socket, Packet packet);

}