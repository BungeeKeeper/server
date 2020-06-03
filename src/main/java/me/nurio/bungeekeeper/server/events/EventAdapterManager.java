package me.nurio.bungeekeeper.server.events;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.server.events.adapters.*;
import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;
import me.nurio.events.handler.Event;

public class EventAdapterManager {

    public static Event getEvent(ConnectionSocket socket, Packet packet) {
        EventAdapter adapter = getAdapter(packet.getId());
        if (adapter == null) throw new RuntimeException("Can't found adapter for (" + packet.getClass().getName() + ":" + packet.getId() + ")");
        return adapter.getEvent(socket, packet);
    }

    public static EventAdapter getAdapter(byte packetId) {
        if (packetId == 1) return new BungeeConnectEventAdapter();
        if (packetId == 2) return new BungeeDisconnectEventAdapter();

        if (packetId == 20) return new PlayerPingHandshakeEventAdapter();
        if (packetId == 21) return new PlayerConnectingEventAdapter();
        if (packetId == 25) return new PlayerConnectEventAdapter();
        if (packetId == 22) return new PlayerConnectedEventAdapter();
        if (packetId == 23) return new PlayerServerChangeEventAdapter();
        if (packetId == 24) return new PlayerDisconnectEventAdapter();

        return null;
    }

}