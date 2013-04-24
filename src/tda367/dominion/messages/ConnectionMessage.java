package tda367.dominion.messages;

/**
 * A message class for connecting to the server
 */
public class ConnectionMessage implements Message{
	private String name;
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
