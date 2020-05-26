package me.nurio.applicationbase.packets.bungee;

import lombok.*;
import me.nurio.applicationbase.packets.Packet;
import me.nurio.applicationbase.utils.IdentityUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class HandshakePacket implements Packet {

    public static final byte PACKET_ID = 20;

    private long eventId = IdentityUtil.timeBasedId();

    @NonNull private InetSocketAddress address;
    @NonNull private String domain;
    @NonNull private short port;
    @NonNull private int protocol;

    @Override
    public byte getId() {
        return PACKET_ID;
    }

    @Override
    @SneakyThrows
    public void read(DataInputStream inputStream) {
        String inetAddress = inputStream.readUTF();
        short inetPort = inputStream.readShort();
        address = InetSocketAddress.createUnresolved(inetAddress, inetPort);

        domain = inputStream.readUTF();
        port = inputStream.readShort();
        protocol = inputStream.readInt();
    }

    @Override
    @SneakyThrows
    public void write(DataOutputStream outputStream) {
        outputStream.writeByte(PACKET_ID);

        outputStream.writeUTF(address.getHostName());
        outputStream.writeShort(address.getPort());
        outputStream.writeUTF(domain);
        outputStream.writeShort(port);
        outputStream.writeInt(protocol);
    }

}