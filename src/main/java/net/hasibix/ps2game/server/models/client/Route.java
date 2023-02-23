package net.hasibix.ps2game.server.models.client;

public class Route {
    public enum Type {
        Rest_Get,
        Rest_Head,
        Rest_Post,
        Rest_Put,
        Rest_Delete,
        Rest_Connect,
        Rest_Options,
        Rest_Trace,
        Websocket
    }

    public final String name;
    public final String path;
    public final Type routeType;
    public spark.Route run;
    public Class<?> runws;

    public Route(String name, String path, Type routeType, spark.Route run) {
        this.name = name;
        this.path = path;
        this.routeType = routeType;
        this.run = run;
        this.runws = null;
    }

    public Route(String name, String path, Type routeType, Class<?> run) {
        this.name = name;
        this.path = path;
        this.routeType = routeType;
        this.run = null;
        this.runws = run;
    } 
}