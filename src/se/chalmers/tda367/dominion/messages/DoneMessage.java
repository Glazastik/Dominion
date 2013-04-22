package se.chalmers.tda367.dominion.messages;

public class DoneMessage implements Message {
	private String message;
	public DoneMessage(){
		message = "Done";
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public void setMessage(String message) {
		this.message = message;
		
	}

}
