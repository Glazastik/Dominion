package tda367.dominion.client.model;

import com.esotericsoftware.kryonet.Listener;

import tda367.dominion.client.network.ClientConnection;
import tda367.dominion.commons.messages.BoolMessage;
import tda367.dominion.commons.messages.LocatedCardMessage;

public class ClientModel {
	private ClientConnection connection;
	private String[][] roomData;
	
	public ClientModel() {
		this.connection = new ClientConnection();
	}
	
	public void addListener(Listener l) {
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
	
	/**
	 * Tell the server to play the card.
	 * 
	 * @param card to be played.
	 */
	public void playCard(String card) {
		LocatedCardMessage msg = new LocatedCardMessage();
		msg.setCardName(card);
		connection.sendMessage(msg);
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
		BoolMessage msg = new BoolMessage();
		msg.setBool(bool);
		connection.sendMessage(msg);
	}
	
}
