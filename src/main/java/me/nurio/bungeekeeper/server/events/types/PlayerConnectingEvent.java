package me.nurio.bungeekeeper.server.events.types;

import lombok.Data;
import me.nurio.bungeekeeper.server.management.entities.player.PendingConnection;
import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;
import me.nurio.events.handler.Event;

@Data
public class PlayerConnectingEvent extends Event {
    private ConnectionSocket connectionSocket;

    private long eventId;

    private PendingConnection player;

}