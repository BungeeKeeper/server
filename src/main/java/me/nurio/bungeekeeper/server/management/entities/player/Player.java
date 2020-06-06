package me.nurio.bungeekeeper.server.management.entities.player;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.nurio.bungeekeeper.server.management.entities.RemoteAddress;

import java.util.UUID;

/**
 * Represents a Minecraft Player playing on a server.
 */
@Data
@AllArgsConstructor
public class Player implements PlayerConnection, PendingConnection, SwitchingPlayer {

    /**
     * Minecraft player username.
     */
    private String name;

    /**
     * Minecraft player UUID.
     */
    private UUID uniqueId;

    /**
     * Minecraft player playing version.
     */
    private int version;

    /**
     * Minecraft player online mode.
     */
    private boolean premium;

    /**
     * Minecraft player remote address.
     */
    private RemoteAddress address;

    /**
     * Create a Minecraft Player instance using Remote Address parser.
     *
     * @param name     Minecraft player username.
     * @param uniqueId Minecraft player UUID.
     * @param version  Minecraft player playing version.
     * @param premium  Minecraft player online mode.
     * @param address  Minecraft player remote address.
     */
    public Player(String name, UUID uniqueId, int version, boolean premium, String address) {
        this(name, uniqueId, version, premium, new RemoteAddress(address));
    }

    /**
     * Create a Minecraft Player instance using Remote Address parser.
     *
     * @param name    Minecraft player username.
     * @param version Minecraft player playing version.
     * @param address Minecraft player remote address.
     */
    public Player(String name, int version, String address) {
        this.name = name;
        this.version = version;
        this.address = new RemoteAddress(address);
    }

    /**
     * Create a Minecraft Player instance using Remote Address parser.
     *
     * @param name     Minecraft player username.
     * @param uniqueId Minecraft player UUID.
     * @param address  Minecraft player remote address.
     */
    public Player(String name, UUID uniqueId, String address) {
        this.name = name;
        this.uniqueId = uniqueId;
        this.address = new RemoteAddress(address);
    }

}