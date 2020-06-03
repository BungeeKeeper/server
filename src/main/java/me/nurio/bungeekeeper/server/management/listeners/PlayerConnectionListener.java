package me.nurio.bungeekeeper.server.management.listeners;

import me.nurio.bungeekeeper.packets.bungee.ConnectionResponsePacket;
import me.nurio.bungeekeeper.server.events.types.*;
import me.nurio.bungeekeeper.server.logger.Logger;
import me.nurio.events.handler.EventHandler;
import me.nurio.events.handler.EventListener;

public class PlayerConnectionListener implements EventListener {

    @EventHandler
    public void onConnecting(PlayerConnectingEvent event) {
        Logger logger = event.getConnectionSocket().getLogger();
        logger.log("Player '" + event.getPlayerName() + "' is connecting to the server.");
    }

    @EventHandler
    public void onConnect(PlayerConnectEvent event) {
        Logger logger = event.getConnectionSocket().getLogger();
        logger.log("Player '" + event.getPlayerName() + "' has connected to the server.");

        // Temporal response
        ConnectionResponsePacket connectionPacket = new ConnectionResponsePacket(
            event.getEventId(),
            event.getPlayerName(),
            event.getUniqueId(),
            event.getAddress(),
            event.getPlayerName().contains("x"),
            1,
            "Not implemented yet. (" + event.isPremium() + ")"
        );

        event.getConnectionSocket().getOutputQueue().registerPacket(connectionPacket);
    }

    @EventHandler
    public void onConnected(PlayerConnectedEvent event) {
        Logger logger = event.getConnectionSocket().getLogger();
        logger.log("Player '" + event.getPlayerName() + "' has joined to the server.");
    }

    @EventHandler
    public void onDisconnect(PlayerDisconnectEvent event) {
        Logger logger = event.getConnectionSocket().getLogger();
        logger.log("Player '" + event.getPlayerName() + "' disconnected from server.");
    }

    @EventHandler
    public void onServerChange(PlayerServerChangeEvent event) {
        Logger logger = event.getConnectionSocket().getLogger();
        logger.log("Player '" + event.getPlayerName() + "' moved to '" + event.getServerName() + "'.");
    }

    @EventHandler
    public void onPing(PlayerPingEvent event) {
        Logger logger = event.getConnectionSocket().getLogger();
        logger.log(event.getAddress() + " pinged the server.");
    }

    @EventHandler
    public void onHandshake(PlayerHandshakeEvent event) {
        Logger logger = event.getConnectionSocket().getLogger();
        logger.log(event.getAddress() + " is joining to the server.");
    }

}