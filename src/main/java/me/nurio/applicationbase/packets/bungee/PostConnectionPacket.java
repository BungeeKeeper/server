package me.nurio.applicationbase.packets.bungee;

import lombok.*;
import me.nurio.applicationbase.packets.Packet;
import me.nurio.applicationbase.utils.IdentityUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class PostConnectionPacket implements Packet {

    public static final byte PACKET_ID = 22;

    private long eventId = IdentityUtil.timeBasedId();

    @NonNull private String username;
    @NonNull private UUID uuid;
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
        uuid = UUID.fromString(inputStream.readUTF());

        String inetAddress = inputStream.readUTF();
        short inetPort = inputStream.readShort();
        address = InetSocketAddress.createUnresolved(inetAddress, inetPort);

        protocol = inputStream.readInt();
    }

    @Override
    @SneakyThrows
    public void write(DataOutputStream outputStream) {
        outputStream.writeByte(PACKET_ID);

        outputStream.writeUTF(username);
        outputStream.writeUTF(uuid.toString());
        outputStream.writeUTF(address.getHostName());
        outputStream.writeShort(address.getPort());
        outputStream.writeInt(protocol);
    }

}