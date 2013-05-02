package tda367.dominion.commons.messages;

/**
 * A message to be sent when the turn is over 
 */
public class DoneMessage implements Message {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		
	}

}
