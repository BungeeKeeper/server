package me.nurio.bungeekeeper.server.events.types;

import lombok.Data;
import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;
import me.nurio.events.handler.Event;

import java.util.UUID;

@Data
public class PlayerConnectingEvent extends Event {
    private ConnectionSocket connectionSocket;

    private long eventId;

    private String playerName;
    private UUID uniqueId;
    private String address;

}