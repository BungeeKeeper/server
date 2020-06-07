package me.nurio.bungeekeeper.server.listeners;

import me.nurio.bungeekeeper.packets.bungee.ConnectionResponsePacket;
import me.nurio.bungeekeeper.server.events.types.*;
import me.nurio.bungeekeeper.server.logger.Logger;
import me.nurio.bungeekeeper.server.management.entities.player.PendingConnection;
import me.nurio.events.handler.EventHandler;
import me.nurio.events.handler.EventListener;

public class PlayerConnectionListener implements EventListener {

    @EventHandler
    public void onConnecting(PlayerConnectingEvent event) {
        Logger logger = event.getConnectionSocket().getLogger();
        PendingConnection player = event.getPlayer();

        logger.log("Player '" + player.getName() + "' is connecting to the server.");

        // Temporal response
        ConnectionResponsePacket connectionPacket = new ConnectionResponsePacket(
            event.getEventId(),
            player.getName(),
            null,
            player.getAddress().getAddress(),
            player.getName().contains("x"),
            1,
            "Not implemented yet."
        );

        event.getConnectionSocket().getOutputQueue().registerPacket(connectionPacket);
    }

    @EventHandler
    public void onConnect(PlayerConnectEvent event) {
        Logger logger = event.getConnectionSocket().getLogger();
        logger.log("Player '" + event.getPlayer().getName() + "' has connected to the server.");
    }

    @EventHandler
    public void onConnected(PlayerConnectedEvent event) {
        Logger logger = event.getConnectionSocket().getLogger();
        logger.log("Player '" + event.getPlayer().getName() + "' has joined to the server.");
    }

    @EventHandler
    public void onDisconnect(PlayerDisconnectEvent event) {
        Logger logger = event.getConnectionSocket().getLogger();
        logger.log("Player '" + event.getPlayer().getName() + "' disconnected from server.");
    }

    @EventHandler
    public void onServerChange(PlayerServerChangeEvent event) {
        Logger logger = event.getConnectionSocket().getLogger();
        logger.log("Player '" + event.getPlayer().getName() + "' moved to '" + event.getServerName() + "'.");
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