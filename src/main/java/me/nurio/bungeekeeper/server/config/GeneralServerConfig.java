package me.nurio.bungeekeeper.server.config;

import lombok.Data;

@Data
public class GeneralServerConfig {

    private BindConfig bind = new BindConfig();
    private SslConfig ssl = new SslConfig();
    private SqlConfig sql = new SqlConfig();

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

    @Data
    public class SqlConfig {
        private String host = "localhost";
        private String database = "bungeekeeper";
        private int port = 3006;

        private String user = "root";
        private String password = "123456";
    }

}