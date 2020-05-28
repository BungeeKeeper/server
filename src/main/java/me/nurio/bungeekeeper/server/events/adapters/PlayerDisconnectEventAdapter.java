package me.nurio.bungeekeeper.server.events.adapters;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.packets.bungee.DisconnectPacket;
import me.nurio.bungeekeeper.server.events.EventAdapter;
import me.nurio.bungeekeeper.server.events.types.PlayerDisconnectEvent;
import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;
import me.nurio.events.handler.Event;

public class PlayerDisconnectEventAdapter implements EventAdapter {

    @Override
    public int getPacketId(byte id) {
        return 24;
    }

    @Override
    public Event getEvent(ConnectionSocket socket, Packet packet) {
        DisconnectPacket disconnectPacket = (DisconnectPacket) packet;

        PlayerDisconnectEvent event = new PlayerDisconnectEvent();
        event.setAddress(null);
        event.setPlayerName(null);
        event.setUniqueId(null); // TODO
        event.setServerName(disconnectPacket.getServerName());

        event.setConnectionSocket(socket);
        return event;
    }

}