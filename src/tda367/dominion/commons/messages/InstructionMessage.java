package tda367.dominion.commons.messages;

public class InstructionMessage implements Message {
	private String message;
	public InstructionMessage(){
		
	}
	public void setMessage(String message){
		this.message=message;
	}
	public String getMessage(){
		return this.message;
	}
}
