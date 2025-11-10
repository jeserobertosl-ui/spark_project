package org.example.web_sockets;

import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.example.item.Item;

import java.io.IOException;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

@WebSocket
public class ItemWebSocket {

    private static final Queue<Session> sessions = new ConcurrentLinkedDeque<>();

    @OnWebSocketConnect
    public void connected(Session _session)
    {
        sessions.add(_session);
        System.out.println("added session");
    }

    @OnWebSocketClose
    public void closed(Session _session, int _statusCode, String _reason) {
        sessions.remove(_session);
    }

    @OnWebSocketMessage
    public void message(Session _session, String _message) throws IOException {
        System.out.println("Got: " + _message);   // Print message
        _session.getRemote().sendString(_message); // and send it back
    }

    private static void send_to_session(Session session, String message) {
        if (session != null && session.isOpen()) {
            try{
                session.getRemote().sendString(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void send_to_session(Session session, WebSocketMessage web_socket_msg) {
        send_to_session(session, new Gson().toJson(web_socket_msg));
    }

    public static void broadcast_to_all(WebSocketMessage web_socket_msg) {
        String json = new Gson().toJson(web_socket_msg);

        synchronized (sessions){
            sessions.forEach(
                session -> {
                    send_to_session(session, json);
                }
            );
        }
    }

    public static void broadcast_new_item(Item item)
    {
        WebSocketMessage msg = new WebSocketMessage(
            "new_item",
            "new item added",
                Map.of("item", item)
        );

        broadcast_to_all(msg);
    }
}
