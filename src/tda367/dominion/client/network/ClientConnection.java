package tda367.dominion.client.network;

import java.io.IOException;

import tda367.dominion.commons.messages.Message;
import tda367.dominion.commons.network.NetworkCommon;

import com.esotericsoftware.kryonet.Client;
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
	 * Sends a message via TCP.
	 * 
	 * @param msg the message that will be sent
	 */
	public void sendMessage(Message msg) {
		client.sendTCP(msg);
	}
	
	/**
	 * Makes the controller listen to network traffic. 
	 * 
	 * @param l the listener object
	 */
	public void addListener(Listener l) {
		client.addListener(l);
	}
}
