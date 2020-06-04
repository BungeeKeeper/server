package me.nurio.bungeekeeper.server.databases.adapters;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.packets.bungee.ServerChangePacket;
import me.nurio.bungeekeeper.server.ApplicationServer;
import me.nurio.bungeekeeper.server.databases.DatabaseAdapter;
import me.nurio.bungeekeeper.server.logger.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PlayerServerChangeDatabaseAdapter implements DatabaseAdapter {

    private static final Logger logger = Logger.getInstance("Database Adapter", "PlayerConnect");
    private static final String SQL_HISTORY_SERVER_CHANGE_INSERT = "INSERT INTO history_server_switch" +
        "(server_id, `time`, user_username, user_uuid, user_address, user_port, server_name)" +
        "VALUES(?, ?, ?, ?, ?, ?, ?);";

    private Connection sql = ApplicationServer.getDatabase().getConnection();

    @Override
    public void perform(Packet packet) {
        ServerChangePacket serverChangePacket = (ServerChangePacket) packet;
        query(serverChangePacket);
    }

    private void query(ServerChangePacket packet) {
        try {
            String[] address = packet.getAddress().replace("/", "").split(":");

            // Create the mysql insert PreparedStatement
            PreparedStatement preparedStmt = sql.prepareStatement(SQL_HISTORY_SERVER_CHANGE_INSERT);
            preparedStmt.setInt(1, 1);
            preparedStmt.setLong(2, System.currentTimeMillis());
            preparedStmt.setString(3, packet.getPlayerName());
            preparedStmt.setString(4, packet.getUniqueId().toString());
            preparedStmt.setString(5, address[0]);
            preparedStmt.setInt(6, Integer.parseInt(address[1]));
            preparedStmt.setString(7, packet.getServerName());

            // Execute the PreparedStatement
            preparedStmt.execute();
        } catch (Exception er) {
            logger.error("Something went wrong executing query.");
            er.printStackTrace();
            ApplicationServer.getDatabase().checkConnection();
        }
    }

}