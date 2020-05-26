package me.nurio.bungeekeeper.server.sockets;

import lombok.Getter;
import lombok.Setter;
import me.nurio.bungeekeeper.server.sockets.connection.ConnectionSocket;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;

public class MasterSocket {

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
            // Create a SSLServerSocket
            char[] password = "123".toCharArray();

            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(new FileInputStream("keystore.p12"), password);

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("NewSunX509");
            keyManagerFactory.init(keyStore, password);

            SSLContext context = SSLContext.getInstance("TLSv1.2");//"SSL" "TLS"
            context.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
            SSLContext.setDefault(context);

            final SSLServerSocketFactory factory = context.getServerSocketFactory();

            sslServerSocket = (SSLServerSocket) factory.createServerSocket(6060);
        } catch (Exception er) {

        }
    }

    public void accept() {
        try {
            // Listen for one connection
            SSLSocket sslSocket = (SSLSocket) sslServerSocket.accept();
            ConnectionSocket con = new ConnectionSocket(sslSocket);
            con.start();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print(e);
        }
    }
}