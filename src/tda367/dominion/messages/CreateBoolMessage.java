package tda367.dominion.messages;

/**
 * A message class used to tell the view to create a yes/no option
 * @author christoffer
 *
 */
public class CreateBoolMessage implements Message {
	private String message;
	/**
	 * The constructor
	 * @param message the message for the view to display i.e. "Put on top of deck?"
	 */
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
