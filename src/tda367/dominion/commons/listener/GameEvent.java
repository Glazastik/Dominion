package tda367.dominion.commons.listener;

public class GameEvent {
	private String text;
	private boolean bool;
	
	public GameEvent() {

	}
	
	public GameEvent(boolean bool) {
		this.bool = bool;
	}
	
	public GameEvent(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public boolean getBool() {
		return bool;
	}
}
