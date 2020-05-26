package me.nurio.applicationbase.packets.bungee;

import lombok.*;
import me.nurio.applicationbase.packets.Packet;
import me.nurio.applicationbase.utils.IdentityUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ServerChangePacket implements Packet {

    public static final byte PACKET_ID = 23;

    private long eventId = IdentityUtil.timeBasedId();

    @NonNull private String serverName;

    @Override
    public byte getId() {
        return PACKET_ID;
    }

    @Override
    @SneakyThrows
    public void read(DataInputStream inputStream) {
        serverName = inputStream.readUTF();
    }

    @Override
    @SneakyThrows
    public void write(DataOutputStream outputStream) {
        outputStream.writeByte(PACKET_ID);
        outputStream.writeUTF(serverName);
    }

}