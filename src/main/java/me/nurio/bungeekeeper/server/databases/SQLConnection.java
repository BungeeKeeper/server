package me.nurio.bungeekeeper.server.databases;

import lombok.Getter;
import me.nurio.bungeekeeper.server.config.ConfigManager;
import me.nurio.bungeekeeper.server.config.GeneralServerConfig;
import me.nurio.bungeekeeper.server.logger.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection {

    private static final Logger logger = Logger.getInstance("Database");

    private GeneralServerConfig config = ConfigManager.getConfig();

    @Getter private Connection connection;

    public void connect() {
        // Setup driver
        setupDriver();

        // Connect to the MySQL server
        logger.log("Connecting to '%s' mysql server...", config.getSql().getHost());
        try {
            connection = DriverManager.getConnection(
                "jdbc:mysql://" + config.getSql().getHost() + ":" + config.getSql().getPort() + "/" + config.getSql().getDatabase(),
                config.getSql().getUser(),
                config.getSql().getPassword()
            );
            testConnection();
        } catch (SQLException ex) {
            logger.error("Failed to connect to the database");
            logger.error(ex.getMessage());
            System.exit(1);
        }
    }

    public void disconnect() throws SQLException {
        connection.close();
    }

    public void checkConnection() {
        if (isConnected()) return;
        try {
            disconnect();
        } catch (Exception exception) {
            // Nothing at all
        }
        connect();
    }

    private void setupDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("Failed to setup MySQL driver.");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private boolean isConnected() {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery("SELECT 1");
            return true;
        } catch (Exception exception) {
            logger.error("Something went wrong while executing a test query...");
            logger.error(exception.getMessage());
            return false;
        }
    }

    private void testConnection() {
        if (!isConnected()) {
            System.exit(1);
            return;
        }
        logger.log("Connected to the database.");
    }

}