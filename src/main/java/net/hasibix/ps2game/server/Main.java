package net.hasibix.ps2game.server;

import java.time.LocalDateTime;
import io.github.cdimascio.dotenv.Dotenv;
import net.hasibix.ps2game.server.utils.Config;
import net.hasibix.ps2game.server.utils.Logger;

public class Main {
    public static LocalDateTime startTime;
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().load();
        Config.load();
        Logger.setPath("logs/");

        startTime = LocalDateTime.now();
    }
}
