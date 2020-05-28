package me.nurio.bungeekeeper.server.management.listeners;

import me.nurio.bungeekeeper.server.events.types.BungeeConnectEvent;
import me.nurio.bungeekeeper.server.events.types.BungeeDisconnectEvent;
import me.nurio.events.handler.EventHandler;
import me.nurio.events.handler.EventListener;
import me.nurio.events.handler.EventPriority;

public class BungeeConnectionListener implements EventListener {

    @EventHandler
    public void onConnection(BungeeConnectEvent event) {
        String server = event.getServerAddress();
        System.out.println("Connected ['" + server + "'] to the system.");
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onDisconnect(BungeeDisconnectEvent event) {
        String server = event.getServerAddress();
        System.out.println("Disconnected ['" + server + "'] from the system.");
        event.getConnectionSocket().disconnect();
    }

}