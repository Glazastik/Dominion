package tda367.dominion.commons.messages;

public class RoomMessage implements Message{
	private String[][] rooms;

	/**
	 * @return the rooms
	 */
	public String[][] getRooms() {
		return rooms;
	}

	/**
	 * @param rooms the rooms to set
	 */
	public void setRooms(String[][] rooms) {
		this.rooms = rooms;
	}
	
	
}
