package me.nurio.bungeekeeper.server.events.types;

import lombok.Data;
import me.nurio.bungeekeeper.server.management.entities.player.SwitchingPlayer;
import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;
import me.nurio.events.handler.Event;

@Data
public class PlayerServerChangeEvent extends Event {
    private ConnectionSocket connectionSocket;

    private SwitchingPlayer player;
    private String serverName;

}