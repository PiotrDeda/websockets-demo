package zti.websocketsdemo.example2;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/chat/{username}", encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class ChatWebsocket
{
	private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

	private static void broadcast(String message, String textColor) throws IOException, EncodeException
	{
		for (Session session : sessions)
			session.getBasicRemote().sendObject(new Message(message, textColor));
	}

	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) throws IOException, EncodeException
	{
		session.getUserProperties().put("username", username);
		sessions.add(session);
		broadcast(username + " has joined the chat.", "#AA0000");
	}

	@OnMessage
	public void onMessage(Session session, Message message) throws IOException, EncodeException
	{
		String username = (String) session.getUserProperties().get("username");
		broadcast(username + ": " + message.getMessage(), message.getTextColor());
	}

	@OnClose
	public void onClose(Session session) throws IOException, EncodeException
	{
		sessions.remove(session);
		String username = (String) session.getUserProperties().get("username");
		broadcast(username + " has left the chat.", "#AA0000");
	}

	@OnError
	public void onError(Session session, Throwable throwable)
	{
		System.out.println("Error in session " + session.getId());
		throwable.printStackTrace();
	}
}
