package me.nurio.bungeekeeper.server.events.types;

import lombok.Data;
import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;
import me.nurio.events.handler.Event;

@Data
public class PlayerHandshakeEvent extends Event {
    private ConnectionSocket connectionSocket;

    private String address;
    private String domain;
    private int port;
    private int protocol;

}