package me.nurio.bungeekeeper.server.management;

import me.nurio.bungeekeeper.packets.Packet;
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

        if (packet.getId() == 21) return new PlayerConnectingEventCaller(socket, packet);
        if (packet.getId() == 22) return new PlayerConnectedEventCaller(socket, packet);
        if (packet.getId() == 25) return new PlayerConnectEventCaller(socket, packet);
        if (packet.getId() == 24) return new PlayerDisconnectEventCaller(socket, packet);
        return null;
    }

}