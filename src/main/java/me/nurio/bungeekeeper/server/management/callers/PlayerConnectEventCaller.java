package me.nurio.bungeekeeper.server.management.callers;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.packets.bungee.ConnectionResponsePacket;
import me.nurio.bungeekeeper.server.events.EventAdapterManager;
import me.nurio.bungeekeeper.server.events.types.PlayerConnectEvent;
import me.nurio.bungeekeeper.server.management.EventCaller;
import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;
import me.nurio.events.EventManager;

/**
 * This class calls PlayerConnectEvent and manages post actions.
 */
public class PlayerConnectEventCaller implements EventCaller {

    private ConnectionSocket socket;
    private PlayerConnectEvent event;

    public PlayerConnectEventCaller(ConnectionSocket socket, Packet packet) {
        this.socket = socket;
        this.event = (PlayerConnectEvent) EventAdapterManager.getEvent(socket, packet);
    }

    /**
     * Call the event, wait for listeners and perform finally actions.
     */
    public void call() {
        EventManager.callEvent(event);

        // In case some event listener was disallowed the connection.
        if (!event.isAllowed()) {
            disallow(2, event.getDisallowReason());
            return;
        }

        // In case some event listener has cancelled the event.
        if (event.isCancelled()) {
            disallow(1, "You connection has been blocked");
            return;
        }

        allow();
    }

    /**
     * Disallow a player connection.
     *
     * @param reason  Disallow reason ID.
     * @param message Disallow kick message.
     */
    private void disallow(int reason, String message) {
        sendResponse(false, reason, message);
    }

    /**
     * Allow a player connection.
     */
    private void allow() {
        sendResponse(true, 0, "allowed");
    }

    /**
     * Send allow or disallow response to the remote BungeeCord server.
     *
     * @param allowed  Is the player allowed?
     * @param reasonId Disallow reason ID.
     * @param message  Disallow kick message.
     */
    private void sendResponse(boolean allowed, int reasonId, String message) {
        ConnectionResponsePacket connectionPacket = new ConnectionResponsePacket(
            event.getEventId(),
            event.getPlayer().getName(),
            null,
            event.getPlayer().getAddress().getAddress(),
            allowed,
            reasonId,
            message
        );
        socket.getOutputQueue().registerPacket(connectionPacket);
    }

}