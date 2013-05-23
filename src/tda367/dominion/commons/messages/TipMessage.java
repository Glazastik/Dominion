package tda367.dominion.commons.messages;

/**
 * Tells the client(s) to update their tip box.
 *
 */
public class TipMessage implements Message {
	private String message;

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
