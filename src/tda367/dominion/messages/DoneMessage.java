package tda367.dominion.messages;

public class DoneMessage implements InstructionMessage {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		
	}

}
