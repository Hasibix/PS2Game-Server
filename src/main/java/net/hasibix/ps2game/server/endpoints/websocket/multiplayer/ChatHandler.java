package net.hasibix.ps2game.server.endpoints.websocket.multiplayer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import net.hasibix.ps2game.server.models.client.Message;
import net.hasibix.ps2game.server.models.client.MessageDecoder;
import net.hasibix.ps2game.server.models.client.MessageEncoder;
import net.hasibix.ps2game.server.models.client.User;
import net.hasibix.ps2game.server.utils.Logger;

@ServerEndpoint(value="/servers/{serverID}/chat", decoders=MessageDecoder.class, encoders=MessageEncoder.class)
public class ChatHandler {
    private Session session;
    private static Set<ChatHandler> chatHandlers = new CopyOnWriteArraySet<>();
    private static HashMap<String, String> users = new HashMap<>();
    private static Logger logger = Logger.of(ChatHandler.class);

    @OnOpen
    public void onOpen(Session session, @PathParam("serverID") String serverID, @PathParam("userID") String userID) {
        this.session = session;
        chatHandlers.add(this);
        users.put(session.getId(), userID);

        Message message = new Message();
        message.setFrom("Server");
        message.setContent("Connected to chats of server  " + serverID + ".");
        broadcast(message);
    }

    @OnMessage
    public void onMessage(Session session, Message message) 
      throws IOException {
 
        message.setFrom(users.get(session.getId()));
        broadcast(message);
    }

    @OnClose
    public void onClose(Session session) throws IOException {
 
        chatHandlers.remove(this);
        Message message = new Message();
        message.setFrom(users.get(session.getId()));
        message.setContent("Disconnected!");
        broadcast(message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.error("An exception occured!");
        logger.trace(throwable);
    }

    private static void broadcast(Message message) {
 
        chatHandlers.forEach(endpoint -> {
            synchronized (endpoint) {
                try {
                    endpoint.session.getBasicRemote().
                    sendObject(message);
                } catch (IOException | EncodeException e) {
                    logger.error("An exception occurred!");
                    logger.trace(e);
                }
            }
        });
    }
}
