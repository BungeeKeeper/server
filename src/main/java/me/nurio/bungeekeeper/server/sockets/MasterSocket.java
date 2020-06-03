package me.nurio.bungeekeeper.server.sockets;

import lombok.Getter;
import lombok.Setter;
import me.nurio.bungeekeeper.server.config.ConfigManager;
import me.nurio.bungeekeeper.server.config.GeneralServerConfig;
import me.nurio.bungeekeeper.server.logger.Logger;
import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.security.KeyStore;

public class MasterSocket {

    private static GeneralServerConfig config = ConfigManager.getConfig();

    private Logger logger = Logger.getInstance("Master");

    private SSLServerSocket sslServerSocket;
    @Getter @Setter boolean listening = true;

    public MasterSocket() {
        setupSslSocket();
        startListening();
    }

    private void startListening() {
        while (listening) {
            accept();
        }
    }

    public void setupSslSocket() {
        try {
            // SSL Certificate
            GeneralServerConfig.SslConfig sslConfig = config.getSsl();

            char[] password = sslConfig.getPassphase().toCharArray();

            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(new FileInputStream(sslConfig.getCertificate()), password);

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("NewSunX509");
            keyManagerFactory.init(keyStore, password);

            // SSL Context
            SSLContext context = SSLContext.getInstance("TLSv1.2");
            context.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
            SSLContext.setDefault(context);

            final SSLServerSocketFactory factory = context.getServerSocketFactory();

            // SSL Socket
            GeneralServerConfig.BindConfig bind = config.getBind();
            InetAddress inetAddress = InetAddress.getByName(bind.getAddress());
            sslServerSocket = (SSLServerSocket) factory.createServerSocket(bind.getPort(), 0, inetAddress);

            logger.log("BungeeKeeper is now listening to %s:%s", inetAddress.getHostAddress(), bind.getPort());
        } catch (Exception er) {
            logger.error("Failed to start BungeeKeeper.");
            logger.error(er.getMessage());
            System.exit(1);
        }
    }

    public void accept() {
        try {
            // Listen for one connection
            SSLSocket sslSocket = (SSLSocket) sslServerSocket.accept();
            ConnectionSocket con = new ConnectionSocket(sslSocket);
            con.start();
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
}