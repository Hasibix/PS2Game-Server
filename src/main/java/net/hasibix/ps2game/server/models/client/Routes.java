package net.hasibix.ps2game.server.models.client;

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
    
        public final String path;
        public final Type routeType;

        @FunctionalInterface
        public interface ReturningAction<A, D> {
            D run(A a);
        }

        @FunctionalInterface
        public interface BiReturningAction<A, B, D> {
            D run(A a, B b);
        }

        @FunctionalInterface
        public interface TriReturningAction<A, B, C, D> {
            D run(A a, B b, C c);
        }

        public final BiReturningAction<Request, Response, ?> action;
    
        public Rest(String path, Type routeType,  BiReturningAction<Request, Response, ?> action) {
            this.path = path;
            this.routeType = routeType;
            this.action = action;
        }
    }

    public static class WebSocket {
        public final String path;

        @FunctionalInterface
        public interface Action<A> {
            void run(A a);
        }

        @FunctionalInterface
        public interface BiAction<A, B> {
            void run(A a, B b);
        }

        @FunctionalInterface
        public interface TriAction<A, B, C> {
            void run(A a, B b, C c);
        }

        @Nullable public final Action<Session> onConnect;
        @Nullable public final BiAction<Session, String> onMessage;
        @Nullable public final BiAction<Session, Throwable> onError;
        @Nullable public final TriAction<Session, Integer, String> onClose;
        
        public WebSocket(String path, @Nullable Action<Session> onConnect, @Nullable BiAction<Session, String> onMessage, @Nullable BiAction<Session, Throwable> onError, @Nullable TriAction<Session, Integer, String> onClose) {
            this.path = path;
            this.onConnect = onConnect;
            this.onMessage = onMessage;
            this.onError = onError;
            this.onClose = onClose;
        }
    }
}
