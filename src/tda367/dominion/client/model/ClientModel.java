package tda367.dominion.client.model;

import tda367.dominion.client.network.ClientConnection;
import tda367.dominion.commons.messages.BoolMessage;
import tda367.dominion.commons.messages.ConnectionMessage;
import tda367.dominion.commons.messages.LocatedCardMessage;
import tda367.dominion.commons.messages.RoomHostMessage;
import tda367.dominion.commons.messages.RoomUpdateMessage;

import com.esotericsoftware.kryonet.Listener;

public class ClientModel {
	private ClientConnection connection;

	public ClientModel() {
		this.connection = new ClientConnection();
	}

	public void addListener(Listener l) {
		connection.addListener(l);
	}

	public void searchForGame() {
		RoomUpdateMessage msg = new RoomUpdateMessage();
		connection.sendMessage(msg);
	}

	public void joinRoom(int roomId) {
		ConnectionMessage msg = new ConnectionMessage();
		msg.setRoomId(roomId);
		connection.sendMessage(msg);
		System.out.println("Connectionmessage sent");
	}

	public void hostRoom(int i) {
		RoomHostMessage msg = new RoomHostMessage();
		msg.setName("Host: " + Settings.getName());
		connection.sendMessage(msg);
	}

	/**
	 * @param roomData
	 *            the roomData to set
	 */
	// public void setRoomData(String[][] roomData) {
	// view.updateRoomData(roomData);
	// }

	/**
	 * Tell the server to play the card.
	 * 
	 * @param card
	 *            to be played.
	 */
	public void playCard(String card) {
		LocatedCardMessage msg = new LocatedCardMessage();
		msg.setCardName(card);
		connection.sendMessage(msg);
	}

	public void supplyCard(String card) {
		// TODO: Send a SupplyMessage?
	}

	// public void updateCards(CardUpdateMessage msg) {
	// view.updateCards(msg.getHand(), msg.getInPlay(), msg.getDiscard(),
	// msg.getDeckSize());
	// }
	//
	// public void updateStat(PlayerUpdateMessage msg) {
	// view.updatePlayer(msg.getActions(), msg.getBuys(), msg.getMoney());
	// }

	/**
	 * A boolean response from the view.
	 */
	public void boolMessage(boolean bool) {
		BoolMessage msg = new BoolMessage();
		msg.setBool(bool);
		connection.sendMessage(msg);
	}

}
