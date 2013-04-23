package tda367.dominion.messages;

/**
 * A message class for connecting to the server
 */
public class ConnectionMessage implements Message{
	private final String name;
	
	public ConnectionMessage(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
