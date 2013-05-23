package tda367.dominion.client.network;

import java.io.IOException;
import java.net.InetAddress;

import javax.swing.JOptionPane;

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
	
	/**
	 * Connects to the server
	 */
	private void connect() {
		new Thread("Connect") {
			public void run() {
				try {
					// String host = JOptionPane.showInputDialog("Host:");
					InetAddress lol = client.discoverHost(NetworkCommon.UDPPORT, 1000);
					if(lol == null){
						lol = InetAddress.getLocalHost();
					}
					client.connect(5000, lol, NetworkCommon.TCPPORT, NetworkCommon.UDPPORT);
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
		connect();
	}
}