package se.chalmers.tda367.dominion.messages;

/**
 * A message used to deliver a players yes/no choice
 * @author christoffer
 *
 */
public class YesNoMessage implements Message {
	private Boolean bool;
	public YesNoMessage(boolean bool) {
		this.bool = bool;
	}

	@Override
	public String getMessage() {
		return bool.toString();
	}

	@Override
	public void setMessage(String message) {
		//TODO: Should not be used.
	}
	public boolean answerIsYes(){
		return bool;
	}

}
