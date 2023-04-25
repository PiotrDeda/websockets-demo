package zti.websocketsdemo.example1;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;

@ServerEndpoint("/example")
public class ExampleWebsocket
{
	@OnOpen
	public void onOpen(Session session) throws IOException
	{
		String message = "WebSocket connection opened for session: " + session.getId();
		System.out.println(message);
		session.getBasicRemote().sendText(message);
	}

	@OnMessage
	public void onMessage(Session session, String message) throws IOException
	{
		System.out.println("Received message: " + message);
		session.getBasicRemote().sendText("[Echo] " + message);
	}

	@OnClose
	public void onClose(Session session) throws IOException {
		System.out.println("WebSocket connection closed.");
		session.getBasicRemote().sendText("WebSocket connection closed."); // throws java.lang.IllegalStateException
	}

	@OnError
	public void onError(Throwable error) {
		System.out.println("WebSocket error occurred: " + error.getMessage());
	}
}
