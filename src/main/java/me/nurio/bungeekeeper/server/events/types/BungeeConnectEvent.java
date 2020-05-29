package me.nurio.bungeekeeper.server.events.types;

import lombok.Data;
import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;
import me.nurio.events.handler.Event;
import me.nurio.events.handler.EventCancellable;

@Data
public class BungeeConnectEvent extends Event implements EventCancellable {
    // Connection data
    private ConnectionSocket connectionSocket;
    private String address;

    // Provided data
    private int serverType;
    private String serverAddress;
    private int serverPort;

    private String owner;
    private String licence;
    private boolean cancelled;

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
        // handle
    }

}