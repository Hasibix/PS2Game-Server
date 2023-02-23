package net.hasibix.ps2game.server.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class EnvVars {
    private static final Logger logger = Logger.of(EnvVars.class);
    private static Map<String, String> variables;

    public static void load() {
        variables = System.getenv();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(".env"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    variables.putIfAbsent(key, value);
                }
            }
            br.close();
        } catch (IOException e) {
            logger.error("An exception occurred while trying to load environment variables!");
            logger.trace(e);
        }
    }

    public static String get(String key) {
        return variables.get(key);
    }
}
