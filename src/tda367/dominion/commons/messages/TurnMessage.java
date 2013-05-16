package tda367.dominion.commons.messages;

public class TurnMessage implements Message {
	private Phase phase;
	public enum Phase{
		ACTION, BUY;
	}

	public void setPhase(Phase p){
		phase = p;
	}
	
	public Phase getPhase(){
		return phase;
	}
}
