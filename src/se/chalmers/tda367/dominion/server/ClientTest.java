package se.chalmers.tda367.dominion.server;

import java.io.IOException;

import javax.swing.JOptionPane;

import net.java.games.input.EventQueue;

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
		

		client.addListener(new Listener() {
			public void connected(Connection connection) {
				client.sendTCP("Hej");
			}

			public void disconnected(Connection connection) {
				System.out.println("Fuuu");
			}
		});

		client.start();
		new Thread("Connect") {
			public void run() {
				try {
					String host = JOptionPane.showInputDialog("Host:");
					client.connect(5000, host, 54555, 54777);
					// Server communication after connection can go here, or in
					// Listener#connected().
				} catch (IOException ex) {
					ex.printStackTrace();
					System.exit(1);
				}
			}
		}.start();

		// try {
		// Log.set(Log.LEVEL_DEBUG);
		// client.connect(5000, "localhost", 54555, 54777);
		// System.out.println(client.isConnected());
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

	}

}
