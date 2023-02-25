package net.hasibix.ps2game.server.routes.websocket.multiplayer.sockets;

import net.hasibix.ps2game.server.handlers.RoutesHandler;
import net.hasibix.ps2game.server.models.client.Routes;

public class Chats {
    public static void register() {
        RoutesHandler.WebSocket.instance.AddRoute(
            new Routes.WebSocket(
                "/api/v1/socket/multiplayer/rooms/:id/chats",
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
