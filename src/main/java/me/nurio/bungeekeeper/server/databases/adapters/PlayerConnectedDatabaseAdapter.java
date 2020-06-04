package me.nurio.bungeekeeper.server.databases.adapters;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.packets.bungee.PostConnectionPacket;
import me.nurio.bungeekeeper.server.ApplicationServer;
import me.nurio.bungeekeeper.server.databases.DatabaseAdapter;
import me.nurio.bungeekeeper.server.logger.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PlayerConnectedDatabaseAdapter implements DatabaseAdapter {

    private static final Logger logger = Logger.getInstance("Database Adapter", "PlayerConnect");
    private static final String SQL_HISTORY_CONNECTION_INSERT = "INSERT INTO history_connections" +
        "(server_id, `time`, connection_type, user_address, user_port, user_username, user_uuid, user_premium, user_version)" +
        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private Connection sql = ApplicationServer.getDatabase().getConnection();

    @Override
    public void perform(Packet packet) {
        PostConnectionPacket connectionPacket = (PostConnectionPacket) packet;
        query(connectionPacket);
    }

    private void query(PostConnectionPacket packet) {
        try {
            String[] address = packet.getAddress().replace("/", "").split(":");

            // Create the mysql insert PreparedStatement
            PreparedStatement preparedStmt = sql.prepareStatement(SQL_HISTORY_CONNECTION_INSERT);
            preparedStmt.setInt(1, 1);
            preparedStmt.setLong(2, System.currentTimeMillis());
            preparedStmt.setInt(3, 3);
            preparedStmt.setString(4, address[0]);
            preparedStmt.setInt(5, Integer.parseInt(address[1]));
            preparedStmt.setString(6, packet.getUsername());
            preparedStmt.setString(7, packet.getUniqueId().toString());
            preparedStmt.setBoolean(8, packet.isPremium());
            preparedStmt.setInt(9, packet.getProtocol());

            // Execute the PreparedStatement
            preparedStmt.execute();
        } catch (Exception er) {
            logger.error("Something went wrong executing query.");
            er.printStackTrace();
        }
    }

}