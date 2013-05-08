package tda367.dominion.client.model;

import com.esotericsoftware.kryonet.Listener;

import tda367.dominion.client.network.ClientConnection;
import tda367.dominion.commons.messages.CardUpdateMessage;
import tda367.dominion.commons.messages.PlayerUpdateMessage;

public class ClientModel {
	private ClientConnection connection;
	private String[][] roomData;
	
	public ClientModel() {
		this.connection = new ClientConnection();
	}
	
	public void addListener(Listener l) {
		System.out.println("hej");
		connection.addListener(l);
	}
	
	public void searchForGame() {
		connection.connect();
	}

	/**
	 * @param roomData the roomData to set
	 */
//	public void setRoomData(String[][] roomData) {
//		view.updateRoomData(roomData);
//	}
	
	public void cardResponse() {
		
	}
	
	public void playCard(String card) {
		connection.playCard(card);
	}
	
//	public void updateCards(CardUpdateMessage msg) {
//		view.updateCards(msg.getHand(), msg.getInPlay(), msg.getDiscard(), msg.getDeckSize());
//	}
//	
//	public void updateStat(PlayerUpdateMessage msg) {
//		view.updatePlayer(msg.getActions(), msg.getBuys(), msg.getMoney());
//	}
	
	/**
	 * A boolean response from the view.
	 */
	public void boolMessage(boolean bool) {
		connection.boolMessage(bool);
	}
	
}
