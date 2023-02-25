package net.hasibix.ps2game.server;

import java.time.LocalDateTime;
import net.hasibix.ps2game.server.handlers.RoutesHandler;
import net.hasibix.ps2game.server.utils.Config;
import net.hasibix.ps2game.server.utils.EnvVars;
import net.hasibix.ps2game.server.utils.Logger;
import net.hasibix.ps2game.server.utils.Supabase;

public class Main {
    public static LocalDateTime startTime;
    public static void main(String[] args) {
        EnvVars.load();
        Config.load();
        Logger.setPath("logs/");

        //Supabase
        Supabase.Initialize(EnvVars.get("SUPABASE_URL"), EnvVars.get("SUPABASE_API_KEY"));

        //Rest
        RoutesHandler.Rest restRoutesHandler = RoutesHandler.Rest.Intialize();
        restRoutesHandler.LoadRoutes("net.hasibix.ps2game.server.routes");
        restRoutesHandler.Listen(Config.get("rest", "port", Integer.class));

        //WebSocket
        RoutesHandler.WebSocket wsRoutesHandler = RoutesHandler.WebSocket.Intialize();
        wsRoutesHandler.LoadRoutes("net.hasibix.ps2game.server.routes");
        wsRoutesHandler.Listen(Config.get("ws", "port", Integer.class));

        startTime = LocalDateTime.now();
    }
}
