package zti.websocketsdemo.example1;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;

@ServerEndpoint("/example")
public class ExampleWebsocket
{
	private Session session;

	@OnOpen
	public void onOpen(Session newSession) throws IOException
	{
		session = newSession;
		String message = "WebSocket connection opened for session: " + session.getId();
		System.out.println(message);
		this.session.getBasicRemote().sendText(message);
	}

	@OnMessage
	public void onMessage(String message, Session session) throws IOException
	{
		System.out.println("Received message: " + message);
		session.getBasicRemote().sendText("[Echo] " + message);
	}

	@OnClose
	public void onClose() throws IOException {
		System.out.println("WebSocket connection closed.");
		session.getBasicRemote().sendText("WebSocket connection closed."); // throws java.lang.IllegalStateException
		System.out.println("onClose end");
	}

	@OnError
	public void onError(Throwable error) {
		System.out.println("WebSocket error occurred: " + error.getMessage());
	}
}
