package tda367.dominion.messages;

/**
 * A message used to deliver a players yes/no choice
 */
public class BoolMessage {
	private boolean bool;
	
	public void setBool(boolean bool) {
		this.bool = bool;
	}
	
	public boolean getBool() {
		return bool;
	}

}
