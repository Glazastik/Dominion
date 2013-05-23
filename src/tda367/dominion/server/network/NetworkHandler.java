package tda367.dominion.server.network;

import java.io.IOException;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.commons.network.NetworkCommon;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class NetworkHandler {
	private static NetworkHandler network;
	private static Server server;
	
	private NetworkHandler() {
		
	}

	/**
	 * Returns an instance of the network.
	 */
	public static NetworkHandler getInstance() {
		if(network != null) {
			return network;
		}
		
		server = new Server() {
			protected Connection newConnection() {
				return new GameConnection();
			}
		};
		server.start();
		NetworkCommon.register(server);

		try {
			server.bind(NetworkCommon.TCPPORT, NetworkCommon.UDPPORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		network = new NetworkHandler();
		
		return network;
	}

	public void addListener(Listener l) {
		server.addListener(l);
	}

	/**
	 * Send a message to the specific client
	 */
	public void sendMessage(int id, Message msg) {
		server.sendToTCP(id, msg);
	}

}