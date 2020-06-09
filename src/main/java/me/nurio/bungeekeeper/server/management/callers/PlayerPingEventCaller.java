package me.nurio.bungeekeeper.server.management.callers;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.server.events.EventAdapterManager;
import me.nurio.bungeekeeper.server.events.types.PlayerPingEvent;
import me.nurio.bungeekeeper.server.management.EventCaller;
import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;
import me.nurio.events.EventManager;

/**
 * This class calls PlayerPingEvent and manages post actions.
 */
public class PlayerPingEventCaller implements EventCaller {

    private ConnectionSocket socket;
    private PlayerPingEvent event;

    public PlayerPingEventCaller(ConnectionSocket socket, Packet packet) {
        this.socket = socket;
        this.event = (PlayerPingEvent) EventAdapterManager.getEvent(socket, packet);
    }

    /**
     * Call the event, wait for listeners and perform finally actions.
     */
    public void call() {
        EventManager.callEvent(event);
    }

}