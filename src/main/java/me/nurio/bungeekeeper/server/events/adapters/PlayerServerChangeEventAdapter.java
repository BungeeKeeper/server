package me.nurio.bungeekeeper.server.events.adapters;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.packets.bungee.ServerChangePacket;
import me.nurio.bungeekeeper.server.events.EventAdapter;
import me.nurio.bungeekeeper.server.events.types.PlayerServerChangeEvent;
import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;
import me.nurio.events.handler.Event;

public class PlayerServerChangeEventAdapter implements EventAdapter {

    @Override
    public int getPacketId(byte id) {
        return 23;
    }

    @Override
    public Event getEvent(ConnectionSocket socket, Packet packet) {
        ServerChangePacket changePacket = (ServerChangePacket) packet;

        PlayerServerChangeEvent event = new PlayerServerChangeEvent();
        event.setPlayerName(changePacket.getPlayerName());
        event.setUniqueId(changePacket.getUniqueId());
        event.setAddress(changePacket.getAddress());
        event.setServerName(changePacket.getServerName());

        event.setConnectionSocket(socket);
        return event;
    }

}