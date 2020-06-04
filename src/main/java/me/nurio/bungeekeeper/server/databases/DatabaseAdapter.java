package me.nurio.bungeekeeper.server.databases;

import me.nurio.bungeekeeper.packets.Packet;

public interface DatabaseAdapter {

    /**
     * Applies SQL changes to provided packet.
     */
    void perform(Packet packet);

}