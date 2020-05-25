package me.nurio.applicationbase;

import me.nurio.applicationbase.sockets.MasterSocket;

/**
 * Application main class.
 */
public class ApplicationServer {

    private static MasterSocket masterSocket;

    public static void main(String args[]) throws Exception {
        System.out.println("Starting...");
        startMaster();
        System.out.println("Good bye!");
    }

    private static void startMaster(){
        masterSocket = new MasterSocket();
    }

}