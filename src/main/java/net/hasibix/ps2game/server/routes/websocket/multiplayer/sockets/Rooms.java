package net.hasibix.ps2game.server.routes.websocket.multiplayer.sockets;

import net.hasibix.ps2game.server.handlers.RoutesHandler;
import net.hasibix.ps2game.server.models.client.Routes;

public class Rooms {
    public static void Register() {
        RoutesHandler.WebSocket.instance.AddRoute(
            new Routes.WebSocket(
                "/api/v1/ws/multiplayer/rooms/:id",
                (session) -> {
                    
                },
                (session, msg) -> {
                    
                },
                (session, err) -> {
                    
                },
                (session, status, reason) -> {
                    
                }
            )
        );
    }
}
