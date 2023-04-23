package zti.websocketsdemo.example2;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.websocket.Decoder;
import jakarta.websocket.EndpointConfig;

import java.io.StringReader;

public class MessageDecoder implements Decoder.Text<Message>
{
	@Override
	public void init(EndpointConfig endpointConfig)
	{
		System.out.println("Initializing MessageDecoder...");
	}

	@Override
	public boolean willDecode(String jsonMessage)
	{
		try
		{
			Json.createReader(new StringReader(jsonMessage)).readObject();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	@Override
	public Message decode(String jsonMessage)
	{
		JsonObject jsonObject = Json.createReader(new StringReader(jsonMessage)).readObject();
		String message = jsonObject.getString("message");
		String textColor = jsonObject.getString("textColor");
		return new Message(message, textColor);
	}

	@Override
	public void destroy()
	{
		System.out.println("Destroying MessageDecoder...");
	}
}
