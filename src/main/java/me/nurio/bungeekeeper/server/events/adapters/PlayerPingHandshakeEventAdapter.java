package me.nurio.bungeekeeper.server.events.adapters;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.packets.bungee.HandshakePacket;
import me.nurio.bungeekeeper.server.events.EventAdapter;
import me.nurio.bungeekeeper.server.events.types.PlayerHandshakeEvent;
import me.nurio.bungeekeeper.server.events.types.PlayerPingEvent;
import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;
import me.nurio.events.handler.Event;

public class PlayerPingHandshakeEventAdapter implements EventAdapter {

    @Override
    public int getPacketId(byte id) {
        return 20;
    }

    @Override
    public Event getEvent(ConnectionSocket socket, Packet packet) {
        HandshakePacket handshakePacket = (HandshakePacket) packet;
        return handshakePacket.getRequestedProtocol() == 1 ? getPingEvent(socket, handshakePacket) : getHandshakeEvent(socket, handshakePacket);
    }

    public Event getPingEvent(ConnectionSocket socket, HandshakePacket handshakePacket) {
        PlayerPingEvent event = new PlayerPingEvent();
        event.setAddress(handshakePacket.getAddress());
        event.setDomain(handshakePacket.getDomain());
        event.setPort(handshakePacket.getPort());
        event.setProtocol(handshakePacket.getProtocol());

        event.setConnectionSocket(socket);
        return event;
    }

    public Event getHandshakeEvent(ConnectionSocket socket, HandshakePacket handshakePacket) {
        PlayerHandshakeEvent event = new PlayerHandshakeEvent();
        event.setAddress(handshakePacket.getAddress());
        event.setDomain(handshakePacket.getDomain());
        event.setPort(handshakePacket.getPort());
        event.setProtocol(handshakePacket.getProtocol());

        event.setConnectionSocket(socket);
        return event;
    }

}