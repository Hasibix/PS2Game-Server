package net.hasibix.ps2game.server.routes.rest.auth;

import net.hasibix.ps2game.server.handlers.RoutesHandler;
import net.hasibix.ps2game.server.models.client.Routes;

public class Register {
    public static void register() {
        RoutesHandler.Rest.instance.AddRoute(
            new Routes.Rest(
                "/api/v1/rest/auth/register",
                Routes.Rest.Type.Post,
                (req, res) -> {
                    // Action
                    return null;
                }
            )
        );
    }
}
