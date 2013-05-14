package tda367.dominion.commons.messages;

/**
 * A message class for connecting to the server
 */
public class ConnectionMessage implements Message{
	private String name;
	private int roomId;
	
	/**
	 * @return the roomId
	 */
	public int getRoomId() {
		return roomId;
	}

	/**
	 * @param roomId the roomId to set
	 */
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
