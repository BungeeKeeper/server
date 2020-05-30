package me.nurio.bungeekeeper.server.management.listeners;

import me.nurio.bungeekeeper.server.events.types.BungeeConnectEvent;
import me.nurio.bungeekeeper.server.events.types.BungeeDisconnectEvent;
import me.nurio.bungeekeeper.server.logger.Logger;
import me.nurio.events.handler.EventHandler;
import me.nurio.events.handler.EventListener;
import me.nurio.events.handler.EventPriority;

public class BungeeConnectionListener implements EventListener {

    @EventHandler
    public void onConnection(BungeeConnectEvent event) {
        Logger logger = event.getConnectionSocket().getLogger();
        String server = event.getServerAddress();
        logger.log("Server ['" + server + "'] has connected to the system.");
        logger.log("License: " + event.getLicence() + " @ " + event.getOwner());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onDisconnect(BungeeDisconnectEvent event) {
        Logger logger = event.getConnectionSocket().getLogger();
        String server = event.getServerAddress();
        logger.log("Disconnected ['" + server + "'] from the system.");
        event.getConnectionSocket().disconnect();
    }

}