package me.nurio.bungeekeeper.server.config;

import lombok.Data;

@Data
public class GeneralServerConfig {

    private BindConfig bind = new BindConfig();
    private SslConfig ssl = new SslConfig();

    @Data
    public class BindConfig {
        private int port = 6060;
        private String address = "localhost";
    }

    @Data
    public class SslConfig {
        private String certificate = "keystore.p12";
        private String passphase = "123";
    }

}