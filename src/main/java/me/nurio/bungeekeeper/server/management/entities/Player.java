package me.nurio.bungeekeeper.server.management.entities;

import lombok.Data;

import java.net.InetAddress;
import java.util.UUID;

@Data
public class Player {
    private String name;
    private UUID uniqueId;
    private InetAddress address;
}