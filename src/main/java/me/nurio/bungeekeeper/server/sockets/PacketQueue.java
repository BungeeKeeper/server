package me.nurio.bungeekeeper.server.sockets;

import lombok.SneakyThrows;
import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.server.utils.SchedulerUtils;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PacketQueue {

    private Queue<Packet> packetQueue = new ConcurrentLinkedQueue<>();

    public void registerPacket(Packet packet) {
        packetQueue.add(packet);
    }

    public Packet getNextPacket() {
        return packetQueue.poll();
    }

    @SneakyThrows
    public boolean hasPacket() {
        boolean hasNext = !packetQueue.isEmpty();
        if (!hasNext) SchedulerUtils.sleep(5);
        return hasNext;
    }

}