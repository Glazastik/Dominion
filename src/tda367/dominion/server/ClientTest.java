package tda367.dominion.server;

import java.io.IOException;

import tda367.dominion.messages.YesNoMessage;
import tda367.dominion.server.NetworkCommon.BasicMessage;
import tda367.dominion.server.NetworkCommon.PruttMessage;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 * Basic client for connecting to the kryo server.
 * 
 * @author Glazastik
 * 
 */
public class ClientTest {
	static Client client;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		client = new Client();
		client.start();

		NetworkCommon.register(client);

		client.addListener(new Listener() {
			public void connected(Connection c) {
				System.out.println("Yaaay! Connected to the server. :)");
//				PruttMessage msg = new PruttMessage();
				YesNoMessage msg = new YesNoMessage();
				msg.bool = true;
				
				c.sendUDP(msg);
//				c.sendTCP("Testar");
				System.out.println("Messages sent");
			}

			public void received(Connection c, Object object) {
				System.out.println("Object received from server.");
				
			}

			public void disconnected(Connection connection) {
				System.out
						.println("Connection with server has been disconnected.");
				client.stop();
			}
		});

		new Thread("Connect") {
			public void run() {
				try {
//					String host = JOptionPane.showInputDialog("Host:");
					client.connect(5000, "localhost", NetworkCommon.TCPPORT, NetworkCommon.UDPPORT);
					// Server communication after connection can go here, or in
					// Listener#connected().

				} catch (IOException ex) {
					ex.printStackTrace();
					System.exit(1);
				}
			}
		}.start();
	}

}
