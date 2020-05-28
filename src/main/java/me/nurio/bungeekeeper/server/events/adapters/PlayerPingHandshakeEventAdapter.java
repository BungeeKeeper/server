package me.nurio.bungeekeeper.server.events.adapters;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.packets.bungee.HandshakePacket;
import me.nurio.bungeekeeper.server.events.EventAdapter;
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

        // TODO nextStep
        PlayerPingEvent event = new PlayerPingEvent();
        event.setAddress(handshakePacket.getAddress());
        event.setDomain(handshakePacket.getDomain());
        event.setPort(handshakePacket.getPort());
        event.setProtocol(handshakePacket.getProtocol());

        event.setConnectionSocket(socket);
        return event;
    }

}