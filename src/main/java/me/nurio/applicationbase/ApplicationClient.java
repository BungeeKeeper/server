package me.nurio.applicationbase;

import lombok.SneakyThrows;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ApplicationClient {

    @SneakyThrows
    public static void main(String args[]) {
        System.out.println("Connecting...");
        try {
            // Connect to server
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslsocket = (SSLSocket) factory.createSocket("localhost", 1234);

            // Register input and output channels
            DataOutputStream os = new DataOutputStream(sslsocket.getOutputStream());
            DataInputStream is = new DataInputStream(sslsocket.getInputStream());

            // Send message to server
            os.writeByte(1);
            os.writeUTF("Welcome jejeje");
            os.writeBoolean(true);
            os.flush();

            for (int i = 0; i < 1000; i++) {
                os.writeByte(1);
                os.writeUTF("Welcome jejeje");
                os.writeBoolean(true);
                os.flush();
                Thread.sleep(10);
            }

            System.out.println("pop");

            // Listen responses
            String responseStr = is.readUTF();
            System.out.println("pop");

            System.out.println("res: " + responseStr);

            os.close();
            is.close();
            sslsocket.close();
        } catch (IOException e) {
            System.out.println("ex");
            e.printStackTrace();
        }
        System.out.println("Disconnecting...");
    }

}