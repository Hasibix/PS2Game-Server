package net.hasibix.ps2game.server.endpoints.http.multiplayer;

import static spark.Spark.*;
import spark.Request;
import spark.Response;
import spark.Route;

public class JoinServer {
    public void register() {
        post("/servers/:sid/join", new Route() {
            @Override public Object handle(Request request, Response response) {
                String userID = request.queryParams("userID");
                String serverID = request.params(":sid");
                response.status(200);
                return  "Connected to server " + serverID + " as user " + userID + ".";
            }
        });
    }
}
