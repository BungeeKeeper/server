package me.nurio.bungeekeeper.server.management.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents RemoteAddress.
 */
@Data
@AllArgsConstructor
public class RemoteAddress {

    /**
     * IP address.
     */
    private String address;

    /**
     * IP address port.
     */
    private int port;

    /**
     * Parses RemoteAddress using InetAddress format.
     * InetAddress example: "/0.0.0.0:6060"
     *
     * @param address InetAddress address.
     */
    public RemoteAddress(String address) {
        String[] inetAddress = address.replace("\\", "").split(":");
        this.address = inetAddress[0];
        this.port = Integer.parseInt(inetAddress[1]);
    }

    /**
     * InetAddress address.
     *
     * @return Formatted InetAddress address.
     */
    public String getInetAddress() {
        return "\\" + address + ":" + port;
    }

}