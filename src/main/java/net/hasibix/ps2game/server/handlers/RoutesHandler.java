package net.hasibix.ps2game.server.handlers;

import static spark.Spark.*;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
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

    public void Listen(@Nullable int port) {
        for (Route i : this.routes) {
            switch (i.routeType) {
                case Rest_Get:
                    // handle Rest_Get case
                    break;
                case Rest_Head:
                    // handle Rest_Head case
                    break;
                case Rest_Post:
                    // handle Rest_Post case
                    break;
                case Rest_Put:
                    // handle Rest_Put case
                    break;
                case Rest_Delete:
                    // handle Rest_Delete case
                    break;
                case Rest_Connect:
                    // handle Rest_Connect case
                    break;
                case Rest_Options:
                    // handle Rest_Options case
                    break;
                case Rest_Trace:
                    // handle Rest_Trace case
                    break;
                case Websocket:
                    // handle Websocket case
                    break;
            }
        }
    }
}
