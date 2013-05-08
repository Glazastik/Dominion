package tda367.dominion.client.view;

public class ViewEvent {
	private final String text;
	
	public ViewEvent() {
		this("");
	}
	
	public ViewEvent(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
}
