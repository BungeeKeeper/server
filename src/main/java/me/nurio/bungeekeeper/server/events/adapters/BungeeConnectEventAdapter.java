package me.nurio.bungeekeeper.server.events.adapters;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.packets.system.HandshakeSystemPacket;
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
        HandshakeSystemPacket handshakePacket = (HandshakeSystemPacket) packet;

        BungeeConnectEvent event = new BungeeConnectEvent();
        event.setServerAddress(handshakePacket.getServerIp());
        event.setServerPort(handshakePacket.getServerPort());
        event.setServerType(handshakePacket.getServerType());
        event.setOwner(handshakePacket.getOwner());
        event.setLicence(handshakePacket.getLicense());

        event.setAddress(socket.getAddress());
        event.setConnectionSocket(socket);
        return event;
    }

}