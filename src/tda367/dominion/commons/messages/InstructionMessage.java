package tda367.dominion.commons.messages;

public class InstructionMessage implements Message {
	String message;
	public void setMessage(String message){
		this.message=message;
	}
	public String getMessage(){
		return this.message;
	}
}
