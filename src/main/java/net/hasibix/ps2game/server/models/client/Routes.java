package net.hasibix.ps2game.server.models.client;

import java.util.function.BiFunction;
import javax.annotation.Nullable;
import org.eclipse.jetty.websocket.api.Session;
import spark.Request;
import spark.Response;

public class Routes {
    public static class Rest {
        public enum Type {
            Get,
            Head,
            Post,
            Put,
            Delete,
            Connect,
            Options,
            Trace,
        }
    
        public final String name;
        public final String path;
        public final Type routeType;
        public final BiFunction<Request, Response, ?> run;
    
        public Rest(String name, String path, Type routeType,  BiFunction<Request, Response, ?> run) {
            this.name = name;
            this.path = path;
            this.routeType = routeType;
            this.run = run;
        }
    }

    public static class WebSocket {
        public final String name;
        public final String path;

        @FunctionalInterface
        public interface EventFunc<A> {
            void run(A a);
        }

        @FunctionalInterface
        public interface BiEventFunc<A, B> {
            void run(A a, B b);
        }

        @FunctionalInterface
        public interface TriEventFunc<A, B, C> {
            void run(A a, B b, C c);
        }

        @Nullable public final EventFunc<Session> onConnect;
        @Nullable public final BiEventFunc<Session, String> onMessage;
        @Nullable public final BiEventFunc<Session, Throwable> onError;
        @Nullable public final TriEventFunc<Session, Integer, String> onClose;
        
        public WebSocket(String name, String path, @Nullable EventFunc<Session> onConnect, @Nullable BiEventFunc<Session, String> onMessage, @Nullable BiEventFunc<Session, Throwable> onError, @Nullable TriEventFunc<Session, Integer, String> onClose) {
            this.name = name;
            this.path = path;
            this.onConnect = onConnect;
            this.onMessage = onMessage;
            this.onError = onError;
            this.onClose = onClose;
        }
    }
}
