package me.nurio.bungeekeeper.server.management.entities.player;

import me.nurio.bungeekeeper.server.management.entities.RemoteAddress;

import java.util.UUID;

public interface PlayerConnection {

    /**
     * Minecraft player username.
     *
     * @return Player username.
     */
    String getName();

    /**
     * Minecraft player UUID.
     *
     * @return Player UUID.
     */
    UUID getUniqueId();

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

    /**
     * Minecraft player online mode.
     *
     * @return true if player is online mode.
     */
    boolean isPremium();

}