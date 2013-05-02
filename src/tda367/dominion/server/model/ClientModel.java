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
	 * @param roomData the roomData to set
	 */
	public void setRoomData(String[][] roomData) {
		view.updateRoomData(roomData);
	}
	
	public void cardResponse() {
		
	}
	
	/**
	 * A boolean response from the view.
	 */
	public void boolMessage(boolean bool) {
		connection.boolMessage(bool);
	}
	
}
