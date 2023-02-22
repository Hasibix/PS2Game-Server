package net.hasibix.ps2game.server.endpoints.http.multiplayer;

import static spark.Spark.*;
import spark.Request;
import spark.Response;
import spark.Route;

public class DeleteServer {
    public void register() {
        post("/servers/:sid/delete", new Route() {
            @Override public Object handle(Request request, Response response) {
                String ownerToken = request.queryParams("accessToken");
                String serverID = request.params(":sid");
                response.status(200);
                return  "Deleted server " + serverID + ".";
            }
        });
    }
}
