package tda367.dominion.messages;

/**
 * A message class for connecting to the server
 */
public class ConnectionMessage implements InstructionMessage{
	private String name;
	private String roomId;
	
	/**
	 * @return the roomId
	 */
	public String getRoomId() {
		return roomId;
	}

	/**
	 * @param roomId the roomId to set
	 */
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
