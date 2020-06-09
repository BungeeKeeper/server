package me.nurio.bungeekeeper.server.management.callers;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.server.events.EventAdapterManager;
import me.nurio.bungeekeeper.server.events.types.PlayerConnectingEvent;
import me.nurio.bungeekeeper.server.management.EventCaller;
import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;
import me.nurio.events.EventManager;

/**
 * This class calls PlayerConnectingEvent and manages post actions.
 */
public class PlayerConnectingEventCaller implements EventCaller {

    private ConnectionSocket socket;
    private PlayerConnectingEvent event;

    public PlayerConnectingEventCaller(ConnectionSocket socket, Packet packet) {
        this.socket = socket;
        this.event = (PlayerConnectingEvent) EventAdapterManager.getEvent(socket, packet);
    }

    /**
     * Call the event, wait for listeners and perform finally actions.
     */
    public void call() {
        EventManager.callEvent(event);
    }

}