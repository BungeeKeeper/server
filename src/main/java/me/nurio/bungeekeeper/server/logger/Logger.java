package me.nurio.bungeekeeper.server.logger;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Logger {

    @Getter private static final Logger instance = new Logger();

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private String prefix;

    public static Logger getInstance(String... prefixes) {
        String prefix = Arrays.stream(prefixes)
            .map(pref -> "[" + pref + "]")
            .collect(Collectors.joining(" "));
        return new Logger(prefix);
    }

    public void log(String message) {
        String prefix = this.prefix.length() == 0 ? "" : " " + this.prefix;
        System.out.println(getTimestamp() + prefix + " " + message);
    }

    public void error(String message) {
        String prefix = this.prefix.length() == 0 ? "" : " " + this.prefix;
        System.err.println(getTimestamp() + " [Error]" + prefix + " " + message);
    }

    public void log(String message, Object... args) {
        log(String.format(message, args));
    }

    public void error(String message, Object... args) {
        error(String.format(message, args));
    }

    private String getTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return "[" + sdf.format(timestamp) + "]";
    }


}