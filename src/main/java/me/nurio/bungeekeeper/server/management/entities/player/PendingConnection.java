package me.nurio.bungeekeeper.server.management.entities.player;

import me.nurio.bungeekeeper.server.management.entities.RemoteAddress;

public interface PendingConnection {

    /**
     * Minecraft player username.
     *
     * @return Player username.
     */
    String getName();

    /**
     * Minecraft player remote address.
     *
     * @return Player ip address.
     */
    RemoteAddress getAddress();

    /**
     * Minecraft player version.
     *
     * @return Player playing version protocol number.
     */
    int getVersion();

}