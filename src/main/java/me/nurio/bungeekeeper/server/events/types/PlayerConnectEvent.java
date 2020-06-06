package me.nurio.bungeekeeper.server.events.types;

import lombok.Data;
import me.nurio.bungeekeeper.server.management.entities.player.PlayerConnection;
import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;
import me.nurio.events.handler.Event;

@Data
public class PlayerConnectEvent extends Event {

    private ConnectionSocket connectionSocket;

    private long eventId;

    private PlayerConnection player;

}