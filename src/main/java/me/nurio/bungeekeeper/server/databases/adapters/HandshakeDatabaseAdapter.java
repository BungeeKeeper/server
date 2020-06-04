package me.nurio.bungeekeeper.server.databases.adapters;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.packets.bungee.HandshakePacket;
import me.nurio.bungeekeeper.server.ApplicationServer;
import me.nurio.bungeekeeper.server.databases.DatabaseAdapter;
import me.nurio.bungeekeeper.server.logger.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class HandshakeDatabaseAdapter implements DatabaseAdapter {

    private static final Logger logger = Logger.getInstance("Database Adapter", "PlayerHandshake");
    private static final String SQL_HISTORY_PING_INSERT = "INSERT INTO history_ping" +
        "(server_id, `time`, server_domain, server_port, user_address, user_port, user_version)" +
        "VALUES(?, ?, ?, ?, ?, ?, ?);";

    private Connection sql = ApplicationServer.getDatabase().getConnection();

    @Override
    public void perform(Packet packet) {
        HandshakePacket handshakePacket = (HandshakePacket) packet;
        query(handshakePacket);
    }

    private void query(HandshakePacket packet) {
        try {
            String[] address = packet.getAddress().replace("/", "").split(":");

            // Create the mysql insert PreparedStatement
            PreparedStatement preparedStmt = sql.prepareStatement(SQL_HISTORY_PING_INSERT);
            preparedStmt.setInt(1, 1);
            preparedStmt.setLong(2, System.currentTimeMillis());
            preparedStmt.setString(3, packet.getDomain());
            preparedStmt.setInt(4, packet.getPort());
            preparedStmt.setString(5, address[0]);
            preparedStmt.setInt(6, Integer.parseInt(address[1]));
            preparedStmt.setInt(7, packet.getProtocol());

            // Execute the PreparedStatement
            preparedStmt.execute();
        } catch (Exception er) {
            logger.error("Something went wrong executing query.");
            er.printStackTrace();
        }
    }

}