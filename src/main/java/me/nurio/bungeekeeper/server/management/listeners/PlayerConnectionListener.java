package me.nurio.bungeekeeper.server.management.listeners;

import me.nurio.bungeekeeper.server.events.PlayerConnectEvent;
import me.nurio.events.handler.EventHandler;
import me.nurio.events.handler.EventListener;

public class PlayerConnectionListener implements EventListener {

    @EventHandler
    public void onPlayerConnect(PlayerConnectEvent event) {
        System.out.println("Player '" + event.getPlayerName() + "' connected to server.");
    }

}