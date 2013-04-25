package tda367.dominion.client;

import java.io.IOException;

import tda367.dominion.messages.RoomMessage;
import tda367.dominion.server.NetworkCommon;
import tda367.dominion.view.MainView;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ClientFactory {

	public Client createClient() {
		final Client client;
		client = new Client();
		client.start();

		NetworkCommon.register(client);

		client.addListener(new Listener() {
			public void connected(Connection c) {
				System.out.println("Connected to the server on: "
						+ c.getRemoteAddressTCP());

			}

			public void received(Connection c, Object object) {
				System.out.println("Received \"" + object.getClass().getName()
						+ "\" from server.");

				if (object instanceof RoomMessage) {
					RoomMessage rmsg = (RoomMessage) object;
					
					for(String[] s: rmsg.getRooms()){
						System.out.println("Room:");
						System.out.println("Name: " + s[0] + " \n Slots: "+ s[1] + "\n");
					}
				}

			}

			public void disconnected(Connection connection) {
				System.out.println("Disconnected from server..");
				client.stop();
			}
		});

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
		return client;
	}
}
