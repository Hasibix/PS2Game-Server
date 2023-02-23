package net.hasibix.ps2game.server;

import java.time.LocalDateTime;
import net.hasibix.ps2game.server.handlers.RoutesHandler;
import net.hasibix.ps2game.server.utils.Config;
import net.hasibix.ps2game.server.utils.EnvVars;
import net.hasibix.ps2game.server.utils.Logger;

public class Main {
    public static LocalDateTime startTime;
    public static void main(String[] args) {
        EnvVars.load();
        Config.load();
        Logger.setPath("logs/");

        RoutesHandler routesHandler = RoutesHandler.Intialize();
        routesHandler.LoadRoutes("net.hasibix.ps2game.server.routes");
        routesHandler.Listen((int) Config.get("port"));

        startTime = LocalDateTime.now();
    }
}
