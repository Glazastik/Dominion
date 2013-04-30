package tda367.dominion.model;

import tda367.dominion.client.ClientConnection;
import tda367.dominion.view.MainView;

public class ClientModel {
	private ClientConnection connection;
	private MainView view;
	private String[][] roomData;
	
	public ClientModel(ClientConnection connection, MainView view) {
		this.connection = connection;
		this.view = view;
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
