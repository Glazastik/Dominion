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
	
	public boolean answerIsYes(){
		return bool;
	}

}
