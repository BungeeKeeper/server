package me.nurio.bungeekeeper.server.events.types;

import lombok.Data;
import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;
import me.nurio.events.handler.Event;
import me.nurio.events.handler.EventCancellable;

@Data
public class BungeeConnectEvent extends Event implements EventCancellable {
    private ConnectionSocket connectionSocket;

    private String serverAddress;
    private String licence;
    private boolean cancelled;

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
        // handle
    }

}