package me.nurio.bungeekeeper.server.databases;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.server.databases.adapters.HandshakeDatabaseAdapter;
import me.nurio.bungeekeeper.server.databases.adapters.PlayerConnectDatabaseAdapter;
import me.nurio.bungeekeeper.server.databases.adapters.PlayerDisconnectDatabaseAdapter;
import me.nurio.bungeekeeper.server.databases.adapters.PlayerServerChangeDatabaseAdapter;
import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;

public class DatabaseAdapterFactory {

    public static void performQuery(ConnectionSocket socket, Packet packet) {
        DatabaseAdapter adapter = getAdapter(packet.getId());
        if (adapter == null) return; //throw new RuntimeException("Can't found database adapter for (" + packet.getClass().getName() + ":" + packet.getId() + ")");
        adapter.perform(packet);
    }

    public static DatabaseAdapter getAdapter(byte packetId) {
        if (packetId == 20) return new HandshakeDatabaseAdapter();
        if (packetId == 25) return new PlayerConnectDatabaseAdapter();
        if (packetId == 23) return new PlayerServerChangeDatabaseAdapter();
        if (packetId == 24) return new PlayerDisconnectDatabaseAdapter();
        return null;
    }

}