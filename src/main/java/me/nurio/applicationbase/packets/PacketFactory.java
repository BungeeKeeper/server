package me.nurio.applicationbase.packets;

import lombok.RequiredArgsConstructor;
import me.nurio.applicationbase.packets.types.WelcomePacket;

@RequiredArgsConstructor
public class PacketFactory {

    public static Packet getById(byte packetId) {
        if (packetId == 1) return new WelcomePacket();
        return null;
    }

}