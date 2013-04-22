package tda367.dominion.messages;

public class CreateBoolMessage implements Message {
	private String message;
	public CreateBoolMessage(String message){
		this.message = message;
	}
	public String getMessage(){
		return this.message;
	}
	public void setMessage(String message){
		this.message = message;
	}
}
