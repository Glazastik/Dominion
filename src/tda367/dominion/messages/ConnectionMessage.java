package tda367.dominion.messages;

/**
 * A message class for connecting to the server
 */
public class ConnectionMessage implements InstructionMessage{
	private String name;
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
