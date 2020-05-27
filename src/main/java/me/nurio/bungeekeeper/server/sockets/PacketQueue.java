package me.nurio.bungeekeeper.server.sockets;

import lombok.SneakyThrows;
import me.nurio.bungeekeeper.packets.Packet;

import java.util.ArrayList;
import java.util.List;

public class PacketQueue {

    private List<Packet> packetQueue = new ArrayList<>();

    public synchronized void registerPacket(Packet packet) {
        packetQueue.add(packet);
    }

    public synchronized Packet getNextPacket() {
        return packetQueue.remove(0);
    }

    @SneakyThrows
    public synchronized boolean hasPacket() {
        boolean hasNext = packetQueue.size() > 0;
        if (!hasNext) Thread.sleep(5);
        return hasNext;
    }

}