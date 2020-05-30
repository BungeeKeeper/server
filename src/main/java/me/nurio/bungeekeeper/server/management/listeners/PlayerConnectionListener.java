package me.nurio.bungeekeeper.server.management.listeners;

import me.nurio.bungeekeeper.packets.bungee.ConnectionResponsePacket;
import me.nurio.bungeekeeper.server.events.types.*;
import me.nurio.bungeekeeper.server.logger.Logger;
import me.nurio.events.handler.EventHandler;
import me.nurio.events.handler.EventListener;

import java.util.Random;

public class PlayerConnectionListener implements EventListener {

    @EventHandler
    public void onConnecting(PlayerConnectingEvent event) {
        Logger logger = event.getConnectionSocket().getLogger();
        logger.log("Player '" + event.getPlayerName() + "' is connecting to server.");

        // Temporal response
        ConnectionResponsePacket connectionPacket = new ConnectionResponsePacket(
            event.getEventId(),
            event.getPlayerName(),
            event.getUniqueId(),
            event.getAddress().getAddress(),
            new Random().nextBoolean(),
            1,
            "Not implemented yet."
        );

        event.getConnectionSocket().getOutputQueue().registerPacket(connectionPacket);
    }

    @EventHandler
    public void onConnect(PlayerConnectEvent event) {
        Logger logger = event.getConnectionSocket().getLogger();
        logger.log("Player '" + event.getPlayerName() + "' has connected to server.");
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
        logger.log(event.getAddress().getHostString() + " pinged the server.");
    }

    @EventHandler
    public void onHandshake(PlayerHandshakeEvent event) {
        Logger logger = event.getConnectionSocket().getLogger();
        logger.log(event.getAddress().getHostString() + " is joining to the server.");
    }

}