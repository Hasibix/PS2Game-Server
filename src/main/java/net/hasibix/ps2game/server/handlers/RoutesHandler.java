package net.hasibix.ps2game.server.handlers;

import static spark.Spark.*;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import net.hasibix.ps2game.server.models.client.Route;
import net.hasibix.ps2game.server.utils.ClassLoader;
import net.hasibix.ps2game.server.utils.Logger;

public class RoutesHandler {
    public static RoutesHandler instance;
    private static final Logger logger = Logger.of(RoutesHandler.class);
    public List<Route> routes;

    private RoutesHandler() {}

    public static RoutesHandler Intialize() {
        if (instance == null) {
            instance = new RoutesHandler();
        }

        return instance;
    }

    public void LoadRoutes(String routesPackage) {
        try {
            Set<Class<?>> clazzez = ClassLoader.loadFromPackage(routesPackage);
            
            for (Class<?> i : clazzez) {
                Method register = i.getDeclaredMethod(routesPackage);
                register.invoke(null);
            }
        } catch (Exception e) {
            logger.error("An exception occurred while trying to load routes.");
            logger.trace(e);
        }
    }

    public void AddRoute(Route route) {
        if(!this.routes.contains(route)) {
            this.routes.add(route);
        }
    }

    public void Listen(int port) {
        for (Route i : this.routes) {
            switch (i.routeType) {
                case Rest_Get:
                    get(i.path, i.run);
                    break;
                case Rest_Head:
                    head(i.path, i.run);
                    break;
                case Rest_Post:
                    post(i.path, i.run);
                    break;
                case Rest_Put:
                    put(i.path, i.run);
                    break;
                case Rest_Delete:
                    delete(i.path, i.run);
                    break;
                case Rest_Connect:
                    connect(i.path, i.run);
                    break;
                case Rest_Options:
                    options(i.path, i.run);
                    break;
                case Rest_Trace:
                    trace(i.path, i.run);
                    break;
                case Websocket:
                    webSocket(i.path, i.runws);
                    break;
            }
        }
        port(port != -1 ? port : 3000);
    }
}
