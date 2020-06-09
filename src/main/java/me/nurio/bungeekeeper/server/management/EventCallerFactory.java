package me.nurio.bungeekeeper.server.management;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.packets.bungee.HandshakePacket;
import me.nurio.bungeekeeper.server.management.callers.*;
import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;

public class EventCallerFactory {

    /**
     * Obtains EventCaller instance from Packet.
     *
     * @param socket Connection socket of the received packet.
     * @param packet Network received packet.
     * @return EventCaller instance.
     */
    public EventCaller getEventCaller(ConnectionSocket socket, Packet packet) {
        if (packet.getId() == 1) return new BungeeConnectEventCaller(socket, packet);
        if (packet.getId() == 2) return new BungeeDisconnectEventCaller(socket, packet);

        if (packet.getId() == 20) return getHandshakeEventCaller(socket, packet);

        if (packet.getId() == 21) return new PlayerConnectingEventCaller(socket, packet);
        if (packet.getId() == 22) return new PlayerConnectedEventCaller(socket, packet);
        if (packet.getId() == 25) return new PlayerConnectEventCaller(socket, packet);
        if (packet.getId() == 23) return new PlayerServerChangeEventCaller(socket, packet);
        if (packet.getId() == 24) return new PlayerDisconnectEventCaller(socket, packet);
        return null;
    }

    /**
     * This method is a temporal solution to a special packet that can result into different events.
     * Maybe the right solution is using the EventAdapter that already solves the problem.
     *
     * @return PlayerPingEventCaller or PlayerHandshakeEventCaller.
     */
    private EventCaller getHandshakeEventCaller(ConnectionSocket socket, Packet packet) {
        HandshakePacket handshakePacket = (HandshakePacket) packet;
        if (handshakePacket.getRequestedProtocol() == 1) {
            return new PlayerPingEventCaller(socket, packet);
        }
        return new PlayerHandshakeEventCaller(socket, packet);
    }

}