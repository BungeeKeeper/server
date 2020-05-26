package me.nurio.applicationbase.packets;

import lombok.RequiredArgsConstructor;
import me.nurio.applicationbase.packets.bungee.*;
import me.nurio.applicationbase.packets.system.GoodbyeSystemPacket;
import me.nurio.applicationbase.packets.system.HandshakeSystemPacket;
import me.nurio.applicationbase.packets.system.LicenceSystemPacket;

@RequiredArgsConstructor
public class PacketFactory {

    public static Packet getById(byte packetId) {
        if (packetId == 1) return new HandshakeSystemPacket();
        if (packetId == 2) return new GoodbyeSystemPacket();
        if (packetId == 3) return new LicenceSystemPacket();

        if (packetId == 20) return new HandshakePacket();
        if (packetId == 21) return new ConnectionPacket();
        if (packetId == 22) return new PostConnectionPacket();
        if (packetId == 23) return new ServerChangePacket();
        if (packetId == 24) return new DisconnectPacket();

        return null;
    }

}