package tda367.dominion.messages;

public class RoomMessage implements InstructionMessage {
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
