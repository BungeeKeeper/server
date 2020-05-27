package me.nurio.bungeekeeper.server.management.listeners;

import me.nurio.bungeekeeper.server.events.BungeeConnectEvent;
import me.nurio.events.handler.EventHandler;
import me.nurio.events.handler.EventListener;

public class BungeeConnectionListener implements EventListener {

    @EventHandler
    public void onConnection(BungeeConnectEvent event) {
        String server = event.getServerAddress();
        System.out.println("Connected ['" + server + "'] to the system.");
    }

}