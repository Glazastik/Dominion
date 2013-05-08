package tda367.dominion.client.view;

public class ViewEvent {
	private String text;
	private boolean bool;
	
	public ViewEvent() {

	}
	
	public ViewEvent(boolean bool) {
		this.bool = bool;
	}
	
	public ViewEvent(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public boolean getBool() {
		return bool;
	}
}
