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
public class ConnectionPacket implements Packet {

    public static final byte PACKET_ID = 21;

    private long eventId = IdentityUtil.timeBasedId();

    @NonNull private String username;
    @NonNull private InetSocketAddress address;
    @NonNull private int protocol;

    @Override
    public byte getId() {
        return PACKET_ID;
    }

    @Override
    @SneakyThrows
    public void read(DataInputStream inputStream) {
        username = inputStream.readUTF();

        String inetAddress = inputStream.readUTF();
        short port = inputStream.readShort();
        address = InetSocketAddress.createUnresolved(inetAddress, port);

        protocol = inputStream.readInt();
    }

    @Override
    @SneakyThrows
    public void write(DataOutputStream outputStream) {
        outputStream.writeByte(PACKET_ID);
        outputStream.writeUTF(username);
        outputStream.writeUTF(address.getHostName());
        outputStream.writeShort(address.getPort());
        outputStream.writeInt(protocol);
    }

}