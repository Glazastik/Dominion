package tda367.dominion.commons.listener;

public class GameEvent {
	private String text;
	private boolean bool;
	private int number;
	
	public GameEvent() {

	}
	
	public GameEvent(boolean bool) {
		this.bool = bool;
	}
	
	public GameEvent(int number) {
		this.number = number;
	}
	
	public GameEvent(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public int getInt() {
		return number;
	}
	
	public boolean getBool() {
		return bool;
	}
}
