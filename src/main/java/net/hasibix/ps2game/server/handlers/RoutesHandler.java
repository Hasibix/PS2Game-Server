package net.hasibix.ps2game.server.handlers;

import static spark.Spark.*;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;

import net.hasibix.ps2game.server.models.client.Routes;
import net.hasibix.ps2game.server.utils.ClassLoader;
import net.hasibix.ps2game.server.utils.Logger;

public class RoutesHandler {
    public static class Rest {
        public static Rest instance;
        private static final Logger logger = Logger.of(Rest.class);
        public List<Routes.Rest> routes;

        private Rest() {}

        public static Rest Intialize() {
            if (instance == null) {
                instance = new Rest();
            }

            return instance;
        }

        public void LoadRoutes(String routesPackage) {
            try {
                Set<Class<?>> clazzez = ClassLoader.loadFromPackage(routesPackage + ".rest");
                
                for (Class<?> i : clazzez) {
                    Method register = i.getDeclaredMethod("register");
                    register.invoke(null);
                }
            } catch (Exception e) {
                logger.error("An exception occurred while trying to load rest routes.");
                logger.trace(e);
            }
        }

        public void AddRoute(Routes.Rest route) {
            if(!this.routes.contains(route)) {
                this.routes.add(route);
            }
        }

        public void Listen(int port) {
            for (Routes.Rest i : this.routes) {
                switch (i.routeType) {
                    case Get:
                        get(i.path, (req, res) -> i.run.apply(req, res));
                        break;
                    case Head:
                        head(i.path, (req, res) -> i.run.apply(req, res));
                        break;
                    case Post:
                        post(i.path, (req, res) -> i.run.apply(req, res));
                        break;
                    case Put:
                        put(i.path, (req, res) -> i.run.apply(req, res));
                        break;
                    case Delete:
                        delete(i.path, (req, res) -> i.run.apply(req, res));
                        break;
                    case Connect:
                        connect(i.path, (req, res) -> i.run.apply(req, res));
                        break;
                    case Options:
                        options(i.path, (req, res) -> i.run.apply(req, res));
                        break;
                    case Trace:
                        trace(i.path, (req, res) -> i.run.apply(req, res));
                        break;
                }
            }
            port(port != -1 ? port : 3000);
        }
    }

    public static class WebSocket {

        @org.eclipse.jetty.websocket.api.annotations.WebSocket
        private static class Handler {
            private Routes.WebSocket route;
            
            public static Handler Intialize(Routes.WebSocket route) {
                Handler instance = new Handler();
                instance.route = route;
                return instance;
            }

            @OnWebSocketConnect
            public void onConnect(Session session) {
                if(this.route.onConnect != null) {
                    this.route.onConnect.run(session);
                }
            }

            @OnWebSocketMessage
            public void onMessage(Session session, String message) {
                if(this.route.onMessage != null) {
                    this.route.onMessage.run(session, message);
                }
            }

            @OnWebSocketError
            public void onError(Session session, Throwable e) {
                if(this.route.onError != null) {
                    this.route.onError.run(session, e);
                }
            }
            
            @OnWebSocketClose
            public void onClose(Session session, int code, String reason) {
                if(this.route.onClose != null) {
                    this.route.onClose.run(session, code, reason);
                }
            }
        }

        public static WebSocket instance;
        private static final Logger logger = Logger.of(WebSocket.class);
        public List<Routes.WebSocket> routes;

        private WebSocket() {}

        public static WebSocket Intialize() {
            if (instance == null) {
                instance = new WebSocket();
            }

            return instance;
        }

        public void LoadRoutes(String routesPackage) {
            try {
                Set<Class<?>> clazzez = ClassLoader.loadFromPackage(routesPackage + ".websocket");
                
                for (Class<?> i : clazzez) {
                    Method register = i.getDeclaredMethod("register");
                    register.invoke(null);
                }
            } catch (Exception e) {
                logger.error("An exception occurred while trying to load rest routes.");
                logger.trace(e);
            }
        }

        public void AddRoute(Routes.WebSocket route) {
            if(!this.routes.contains(route)) {
                this.routes.add(route);
            }
        }

        public void Listen(int port) {
            for (Routes.WebSocket i : this.routes) {
                webSocket(i.path, Handler.Intialize(i));
            }
            port(port != -1 ? port : 3000);
        }
    }
}
