package io;

import io.javalin.*;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static j2html.TagCreator.*;

public class Chat {
    public static Map<Session, String> userNameMap = new ConcurrentHashMap();

    public static void main(String[] args) {
        Javalin.create()
                .port(7071)
                .enableStaticFiles("/public")
                .ws("/chat", ws -> {
                    ws.onConnect(session ->
                            {
                                String username = "User";
                                userNameMap.put(session, username);
                                broadcastMessage("Server", username + " join the chat");
                            }
                    );
                    ws.onClose((session, status,message) -> {
                        String username = userNameMap.get(session);
                        userNameMap.remove(username);
                        broadcastMessage("Server", username + " left the chat");
                    });
                    ws.onMessage((session, message) -> broadcastMessage(userNameMap.get(session), message));
                }).start();
    }

    //j2html
    private static String createHtmlMessageFromSender(String sender, String message) {
        return article(
                //подкрашиваем жирным кто говорит
                b(sender + " says:"),
                //формат вывода даты в сообщении
                span(attrs(".timestamp"), new SimpleDateFormat("HH:mm:ss").format(new Date())),
                //вывод сообщения
                p(message)
        ).render();
    }

    private static void broadcastMessage(String sender, String message) {
        userNameMap.keySet()
                .stream()
                .filter(Session::isOpen)
                .forEach(
                        session -> {
                            try {
                                session.getRemote().sendString(
                                        new JSONObject()
                                                .put("userMessage", createHtmlMessageFromSender(sender, message))
                                                .put("userlist", userNameMap.values()).toString()
                                );
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                );
    }
}