package tda367.dominion.messages;

/**
 * A message class used to tell the view to create a yes/no option
 * @author christoffer
 * Cards using this message:
 * Library
 * Spy
 * Chancellor
 */
public class CreateBoolMessage implements Message {
	private String message;
	
	public String getMessage(){
		return this.message;
	}
	public void setMessage(String message){
		this.message = message;
	}
}
