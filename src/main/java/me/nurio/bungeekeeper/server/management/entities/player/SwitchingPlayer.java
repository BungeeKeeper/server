package me.nurio.bungeekeeper.server.management.entities.player;

import me.nurio.bungeekeeper.server.management.entities.RemoteAddress;

import java.util.UUID;

public interface SwitchingPlayer {

    /**
     * Minecraft player username.
     *
     * @return Player username.
     */
    String getName();

    /**
     * Minecraft player version.
     *
     * @return Player playing version protocol number.
     */
    UUID getUniqueId();

    /**
     * Minecraft player remote address.
     *
     * @return Player ip address.
     */
    RemoteAddress getAddress();

}