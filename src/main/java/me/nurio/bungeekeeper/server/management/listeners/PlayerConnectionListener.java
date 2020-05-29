package me.nurio.bungeekeeper.server.management.listeners;

import me.nurio.bungeekeeper.packets.bungee.ConnectionResponsePacket;
import me.nurio.bungeekeeper.server.events.types.*;
import me.nurio.events.handler.EventHandler;
import me.nurio.events.handler.EventListener;

public class PlayerConnectionListener implements EventListener {

    @EventHandler
    public void onConnecting(PlayerConnectingEvent event) {
        System.out.println("Player '" + event.getPlayerName() + "' is connecting to server.");
    }

    @EventHandler
    public void onConnect(PlayerConnectEvent event) {
        System.out.println("Player '" + event.getPlayerName() + "' has connected to server.");

        // Temporal response
        ConnectionResponsePacket connectionPacket = new ConnectionResponsePacket(
            event.getEventId(),
            event.getPlayerName(),
            event.getUniqueId(),
            event.getAddress().getAddress(),
            true,
            1,
            "Not implemented yet."
        );
        event.getConnectionSocket().getOutputQueue().registerPacket(connectionPacket);
    }

    @EventHandler
    public void onDisconnect(PlayerDisconnectEvent event) {
        System.out.println("Player '" + event.getPlayerName() + "' disconnected from server.");
    }

    @EventHandler
    public void onServerChange(PlayerServerChangeEvent event) {
        System.out.println("Player '" + event.getPlayerName() + "' moved to '" + event.getServerName() + "'.");
    }

    @EventHandler
    public void onPing(PlayerPingEvent event) {
        System.out.println(event.getAddress().getHostString() + " pinged the server.");
    }

    @EventHandler
    public void onHandshake(PlayerHandshakeEvent event) {
        System.out.println(event.getAddress().getHostString() + " is joining to the server.");
    }

}