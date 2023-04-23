package zti.websocketsdemo.example2;

public class Message {
	private final String message;
	private final String textColor;

	public Message(String message, String textColor) {
		this.message = message;
		this.textColor = textColor;
	}

	public String getMessage() {
		return message;
	}

	public String getTextColor() {
		return textColor;
	}
}
