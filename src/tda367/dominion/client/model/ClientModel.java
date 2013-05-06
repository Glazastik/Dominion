package tda367.dominion.client.model;

import tda367.dominion.client.network.ClientConnection;
import tda367.dominion.client.view.MainView;
import tda367.dominion.commons.messages.CardUpdateMessage;
import tda367.dominion.commons.messages.PlayerUpdateMessage;

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
	
	public void playCard(String card) {
		connection.playCard(card);
	}
	
	public void updateCards(CardUpdateMessage msg) {
		view.updateCards(msg.getHand(), msg.getInPlay(), msg.getDiscard(), msg.getDeckSize());
	}
	
	public void updateStat(PlayerUpdateMessage msg) {
		view.updatePlayer(msg.getActions(), msg.getBuys(), msg.getMoney());
	}
	
	/**
	 * A boolean response from the view.
	 */
	public void boolMessage(boolean bool) {
		connection.boolMessage(bool);
	}
	
}
