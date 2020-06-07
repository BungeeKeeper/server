package me.nurio.bungeekeeper.server.events.types;

import lombok.Getter;
import lombok.Setter;
import me.nurio.bungeekeeper.server.management.entities.player.PlayerConnection;
import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;
import me.nurio.events.handler.Event;

public class PlayerConnectEvent extends Event {

    @Getter @Setter private ConnectionSocket connectionSocket;
    @Getter @Setter private PlayerConnection player;
    @Getter @Setter private long eventId;

    @Getter private boolean allowed;
    @Getter private String disallowReason;

    public void disallow(String reason) {
        allowed = false;
        disallowReason = reason;
    }

    public void allow() {
        allowed = true;
    }

}