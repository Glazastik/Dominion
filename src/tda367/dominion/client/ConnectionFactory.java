package tda367.dominion.client;

import java.io.IOException;

import tda367.dominion.messages.YesNoMessage;
import tda367.dominion.server.NetworkCommon;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ConnectionFactory {
	public Client createClient() {
		final Client client;
		client = new Client();
		client.start();

		NetworkCommon.register(client);

		client.addListener(new Listener() {
			public void connected(Connection c) {
				System.out.println("Connected to the server on: "
						+ c.getRemoteAddressTCP());
				

//				c.sendTCP("");
			}

			public void received(Connection c, Object object) {
				System.out.println("Received object from server");

			}

			public void disconnected(Connection connection) {
				System.out
						.println("Disconnected from server..");
				client.stop();
			}
		});

		new Thread("Connect") {
			public void run() {
				try {
					// String host = JOptionPane.showInputDialog("Host:");
					client.connect(5000, "localhost", NetworkCommon.TCPPORT);
					// Server communication after connection can go here, or in
					// Listener#connected().

				} catch (IOException ex) {
					ex.printStackTrace();
					System.exit(1);
				}
			}
		}.start();
		return client;
	}
}
