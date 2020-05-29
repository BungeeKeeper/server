package me.nurio.bungeekeeper.server;

import me.nurio.bungeekeeper.server.management.listeners.BungeeConnectionListener;
import me.nurio.bungeekeeper.server.management.listeners.PlayerConnectionListener;
import me.nurio.bungeekeeper.server.sockets.MasterSocket;
import me.nurio.events.EventManager;

/**
 * Application main class.
 */
public class ApplicationServer {

    private static MasterSocket masterSocket;

    public static void main(String args[]) throws Exception {
        System.out.println("Starting...");

        System.out.println("Registering events...");
        EventManager.registerEvents(new BungeeConnectionListener());
        EventManager.registerEvents(new PlayerConnectionListener());
        EventManager.setDebugLoggingEnabled(true);

        System.out.println("Starting socket...");
        startMaster();
        System.out.println("Good bye!");
    }

    private static void startMaster() {
        masterSocket = new MasterSocket();
    }

}