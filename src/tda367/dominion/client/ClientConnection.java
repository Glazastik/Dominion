package tda367.dominion.client;

import java.io.IOException;

import tda367.dominion.controller.ClientController;
import tda367.dominion.messages.RoomMessage;
import tda367.dominion.server.NetworkCommon;

import com.esotericsoftware.kryonet.*;

public class ClientConnection extends Listener {
	private ClientController controller;
	private final Client client;
	
	public ClientConnection(ClientController controller) {
		client = new Client();
		client.start();
		
		client.addListener(controller);

		NetworkCommon.register(client);

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
	
	public void connected(Connection c) {
		controller.connected(c);
	}

	public void received(Connection c, Object object) {
		System.out.println("response!");
		controller.received(c, object);
	}

	public void disconnected(Connection c) {
		System.out.println("Disconnected from server..");
		controller.disconnected(c);
	}
	
}