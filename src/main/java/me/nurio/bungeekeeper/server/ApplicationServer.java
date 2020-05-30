package me.nurio.bungeekeeper.server;

import me.nurio.bungeekeeper.server.config.ConfigManager;
import me.nurio.bungeekeeper.server.logger.Logger;
import me.nurio.bungeekeeper.server.management.listeners.BungeeConnectionListener;
import me.nurio.bungeekeeper.server.management.listeners.PlayerConnectionListener;
import me.nurio.bungeekeeper.server.sockets.MasterSocket;
import me.nurio.events.EventManager;

/**
 * Application main class.
 */
public class ApplicationServer {

    private static MasterSocket masterSocket;
    private static Logger logger = Logger.getInstance();

    public static void main(String args[]) throws Exception {
        logger.log("Starting configuration manager...");
        ConfigManager.enable();

        logger.log("Setting up BungeeKeeper event listeners...");
        EventManager.registerEvents(new BungeeConnectionListener());
        EventManager.registerEvents(new PlayerConnectionListener());
        EventManager.setDebugLoggingEnabled(false);

        logger.log("Starting BungeeKeeper socket server...");
        startMaster();
        logger.log("Good bye!");
    }

    private static void startMaster() {
        masterSocket = new MasterSocket();
    }

}