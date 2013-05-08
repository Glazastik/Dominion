package tda367.dominion.client.network;

import java.io.IOException;

import tda367.dominion.commons.messages.BoolMessage;
import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.network.NetworkCommon;
import tda367.dominion.server.cards.ICard;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ClientConnection {
	private final Client client;
	
	public ClientConnection() {
		client = new Client();
		client.start();
		
		NetworkCommon.register(client);
	}
	
	public void connect() {
		new Thread("Connect") {
			public void run() {
				try {
					// String host = JOptionPane.showInputDialog("Host:");
					client.connect(5000, "localhost", NetworkCommon.TCPPORT);

				} catch (IOException ex) {
					System.out.println("Network is borken");
				}
			}
		}.start();
	}
	
	/**
	 * Send a response to the server in the form of a boolean message.
	 */
	public void boolMessage(boolean bool) {
		BoolMessage msg = new BoolMessage();
		msg.setBool(bool);
		client.sendTCP(msg);
	}
	
	/**
	 * Send a response to the server in the form of a card.
	 */
	public void cardMessage(ICard card) {
//		CardMessage msg = new CardMessage();
//		msg.setCard("" + card.getClass());
	}
	
	public void playCard(String card) {
		CardMessage msg = new CardMessage();
		msg.setCard(card);
		client.sendTCP(msg);
	}
	
	public void addListener(Listener l) {
		client.addListener(l);
	}
}
