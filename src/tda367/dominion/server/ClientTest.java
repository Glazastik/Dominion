package tda367.dominion.server;

import java.io.IOException;

import javax.swing.JOptionPane;

import tda367.dominion.messages.YesNoMessage;

import com.esotericsoftware.kryo.Kryo;
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
			public void connected(Connection connection) {
				//
				for (int i = 0; i < 30; i++) {
					client.sendTCP(new YesNoMessage(true));
					client.sendTCP("Testar");
					System.out.println("Messages sent");
				}
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
					String host = JOptionPane.showInputDialog("Host:");
					client.connect(5000, host, NetworkCommon.PORT);
					client.setTimeout(900000);
					client.setKeepAliveTCP(900000);

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
