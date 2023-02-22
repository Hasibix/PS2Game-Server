package net.hasibix.ps2game.server.routes.rest.multiplayer;

import static spark.Spark.*;
import spark.Request;
import spark.Response;
import spark.Route;

public class CreateServer {
    public void register() {
        post("/servers/create", new Route() {
            @Override public Object handle(Request request, Response response) {
                String ownerID = request.queryParams("ownerID");
                String serverName = request.queryParams("serverName");
                String serverID = request.queryParams("serverID");
                response.status(200);
                return  "Created server " + serverID + "!";
            }
        });
    }
}
