package me.nurio.bungeekeeper.server;

import lombok.Getter;
import me.nurio.bungeekeeper.server.config.ConfigManager;
import me.nurio.bungeekeeper.server.databases.SQLConnection;
import me.nurio.bungeekeeper.server.logger.Logger;
import me.nurio.bungeekeeper.server.management.listeners.BungeeConnectionListener;
import me.nurio.bungeekeeper.server.management.listeners.PlayerConnectionListener;
import me.nurio.bungeekeeper.server.sockets.MasterSocket;
import me.nurio.events.EventManager;

/**
 * Application main class.
 */
public class ApplicationServer {

    @Getter private static SQLConnection database;
    private static MasterSocket masterSocket;

    private static Logger logger = Logger.getInstance("Application");

    public static void main(String args[]) throws Exception {
        logger.log("Starting configuration manager...");
        ConfigManager.enable();

        logger.log("Connecting to database...");
        connectSQL();

        logger.log("Setting up BungeeKeeper event listeners...");
        EventManager.registerEvents(new BungeeConnectionListener());
        EventManager.registerEvents(new PlayerConnectionListener());
        EventManager.setDebugLoggingEnabled(false);

        logger.log("Starting BungeeKeeper socket server...");
        startMaster();
        logger.log("Good bye!");
    }

    private static void connectSQL() {
        database = new SQLConnection();
        database.connect();
    }

    private static void startMaster() {
        masterSocket = new MasterSocket();
    }

}