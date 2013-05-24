package tda367.dominion.client.model;

import tda367.dominion.client.network.ClientConnection;
import tda367.dominion.commons.messages.AdvanceMessage;
import tda367.dominion.commons.messages.BoolMessage;
import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.messages.ConnectionMessage;
import tda367.dominion.commons.messages.DoneMessage;
import tda367.dominion.commons.messages.GainMessage;
import tda367.dominion.commons.messages.PlayAllMessage;
import tda367.dominion.commons.messages.RoomHostMessage;
import tda367.dominion.commons.messages.RoomUpdateMessage;

import com.esotericsoftware.kryonet.Listener;

public class ClientModel {
	private ClientConnection connection;
	private String phase;

	public ClientModel() {
		this.connection = new ClientConnection();
	}
	
	public void connect(){
		connection.connect();
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
		msg.setName(Settings.getName());
		connection.sendMessage(msg);
		System.out.println("Connectionmessage sent");
	}

	public void hostRoom(int i) {
		RoomHostMessage msg = new RoomHostMessage();
		msg.setName(Settings.getName());
		connection.sendMessage(msg);
	}

	/**
	 * Tell the server to play the card.
	 * 
	 * @param card
	 *            to be played.
	 */
	public void playCard(String card) {
		if (phase != null && getPhase().equals("action")
				|| getPhase().equals("buy")) {
			CardMessage msg = new CardMessage();
			msg.setCard(card);
			connection.sendMessage(msg);
		}
	}
	
	public void doneCard() {
		DoneMessage msg = new DoneMessage();
		connection.sendMessage(msg);
	}

	public void supplyCard(String card) {
		GainMessage msg = new GainMessage();
		msg.setCard(card);
		connection.sendMessage(msg);
		System.out.println("Sent gainstuff");
	}
	
	public void playAll(){
		connection.sendMessage(new PlayAllMessage());
		System.out.println("Played all treasure!");
	}

	/**
	 * A boolean response from the view.
	 */
	public void boolMessage(boolean bool) {
		BoolMessage msg = new BoolMessage();
		msg.setBool(bool);
		connection.sendMessage(msg);
	}

	public void setPhase(String phase) {
		this.phase = phase;

	}

	public String getPhase() {
		return phase;
	}

	public void nextPhase() {
		AdvanceMessage msg = new AdvanceMessage();
		connection.sendMessage(msg);
	}

}
