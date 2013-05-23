package tda367.dominion.commons.messages;

/**
 * A message used to deliver a players yes/no choice
 */
public class BoolMessage implements Message {
	private boolean bool;
	
	public void setBool(boolean bool) {
		this.bool = bool;
	}
	
	public boolean isTrue() {
		return bool;
	}

}
