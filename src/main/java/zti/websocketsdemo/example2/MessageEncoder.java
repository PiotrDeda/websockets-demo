package zti.websocketsdemo.example2;

import jakarta.json.Json;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Message>
{
	@Override
	public void init(EndpointConfig endpointConfig)
	{
		System.out.println("Initializing MessageEncoder...");
	}

	@Override
	public String encode(Message message)
	{
		return Json.createObjectBuilder()
				.add("message", message.getMessage())
				.add("textColor", message.getTextColor())
				.build().toString();
	}

	@Override
	public void destroy()
	{
		System.out.println("Destroying MessageEncoder...");
	}
}
