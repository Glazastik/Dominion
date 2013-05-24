package tda367.dominion.commons.messages;

/**
 * A message class used to tell the view to create a yes/no option
 * @author christoffer
 * Cards using this message:
 * Library
 * Spy
 * Chancellor
 */
public class CreateBoolMessage implements Message{
	private String text;
	
	public void setText(String s) {
		text = s;
	}
	
	public String getText() {
		return text;
	}
}
