package me.nurio.bungeekeeper.server.management.listeners;

import me.nurio.bungeekeeper.server.events.types.PlayerConnectingEvent;
import me.nurio.bungeekeeper.server.events.types.PlayerDisconnectEvent;
import me.nurio.bungeekeeper.server.events.types.PlayerPingEvent;
import me.nurio.bungeekeeper.server.events.types.PlayerServerChangeEvent;
import me.nurio.events.handler.EventHandler;
import me.nurio.events.handler.EventListener;

public class PlayerConnectionListener implements EventListener {

    @EventHandler
    public void onConnect(PlayerConnectingEvent event) {
        System.out.println("Player '" + event.getPlayerName() + "' connected to server.");
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
        System.out.println(event.getAddress().getAddress() + " pinged the server.");
    }

}