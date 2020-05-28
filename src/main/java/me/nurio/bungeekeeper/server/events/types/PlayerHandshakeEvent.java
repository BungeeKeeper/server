package me.nurio.bungeekeeper.server.events.types;

import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;
import me.nurio.events.handler.Event;

public class PlayerHandshakeEvent extends Event {
    private ConnectionSocket connectionSocket;

}