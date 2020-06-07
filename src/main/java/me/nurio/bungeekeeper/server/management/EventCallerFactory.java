package me.nurio.bungeekeeper.server.management;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.server.management.callers.PlayerConnectEventCaller;
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
        if (packet.getId() == 25) return new PlayerConnectEventCaller(socket, packet);
        return null;
    }

}