package me.nurio.bungeekeeper.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ConfigManager {

    /**
     * YAML file configuration bean mapper.
     */
    private static ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    /**
     * Server properties config
     */
    @Getter private static GeneralServerConfig config = new GeneralServerConfig();

    /**
     * On enable module operations.
     */
    public static void enable() {
        loadConfig();
    }

    /**
     * Load config from disk.
     * In case configuration file doesn't exists. It will create a new one using resources template.
     */
    @SneakyThrows
    private static void loadConfig() {
        File file = new File("connection.yml");
        if (!file.exists()) saveConfig();
        config = mapper.readValue(file, GeneralServerConfig.class);
    }

    /**
     * Saves configuration to file.
     * In case configuration file doesn't exists. It will create a new one using resources template.
     */
    @SneakyThrows
    public static void saveConfig() {
        File file = new File("connection.yml");

        if (!file.exists()) {
            // Configuration file doesn't exists. Create a new one using template contents.
            // This will include comments and blank line separators.
            URL url = ConfigManager.class.getClassLoader().getResource("connection.yml");
            File source = new File(url.getFile());
            Files.copy(source.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return;
        }

        mapper.writeValue(file, config);
    }

}