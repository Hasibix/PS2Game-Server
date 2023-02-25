package net.hasibix.ps2game.server.routes.rest.multiplayer;

import net.hasibix.ps2game.server.handlers.RoutesHandler;
import net.hasibix.ps2game.server.models.client.Routes;

public class DeleteRoom {
    public static void register() {
        RoutesHandler.Rest.instance.AddRoute(
            new Routes.Rest(
                "/api/v1/rest/multiplayer/rooms/:id/delete",
                Routes.Rest.Type.Post,
                (req, res) -> {
                    // Action
                    return null;
                }
            )
        );
    }
}
