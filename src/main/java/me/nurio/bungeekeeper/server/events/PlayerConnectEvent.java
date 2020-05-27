package me.nurio.bungeekeeper.server.events;

import lombok.Data;
import me.nurio.events.handler.Event;

import java.util.UUID;

@Data
public class PlayerConnectEvent extends Event {

    private String playerName;
    private UUID uniqueId;
    private String ipAddress;

}