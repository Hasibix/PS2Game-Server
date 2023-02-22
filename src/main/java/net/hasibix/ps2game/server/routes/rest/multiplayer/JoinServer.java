package net.hasibix.ps2game.server.routes.rest.multiplayer;

import static spark.Spark.*;

public class JoinServer {
    public void register() {
        post("/servers/:sid/join", (req, res) -> {
                String userID = req.queryParams("userID");
                String serverID = req.params(":sid");
                res.status(200);
                return  "Connected to server " + serverID + " as user " + userID + ".";
        });
    }
}
