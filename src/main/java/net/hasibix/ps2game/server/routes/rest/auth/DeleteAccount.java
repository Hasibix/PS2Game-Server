package net.hasibix.ps2game.server.routes.rest.auth;

import net.hasibix.ps2game.server.handlers.RoutesHandler;
import net.hasibix.ps2game.server.models.client.Routes;

public class DeleteAccount {
    public static void register() {
        RoutesHandler.Rest.instance.AddRoute(
            new Routes.Rest(
                "/api/v1/rest/auth/users/:id/delete",
                Routes.Rest.Type.Post,
                (req, res) -> {
                    // Action
                    return null;
                }
            )
        );
    }
}
