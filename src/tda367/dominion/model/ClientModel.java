package tda367.dominion.model;

import tda367.dominion.client.ClientConnection;

public class ClientModel {
	private ClientConnection connection;
	private String[][] roomData;
	
	public ClientModel(ClientConnection connection) {
		this.connection = connection;
	}
	
	public void searchForGame() {
		connection.connect();
	}
	
	public void disconnected() {
		
	}

	/**
	 * @return the roomData
	 */
	public String[][] getRoomData() {
		return roomData;
	}

	/**
	 * @param roomData the roomData to set
	 */
	public void setRoomData(String[][] roomData) {
		this.roomData = roomData;
	}
	
	
}
