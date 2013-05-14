package tda367.dominion.server.network;

import java.io.IOException;
import java.util.ArrayList;

import tda367.dominion.commons.messages.CardUpdateMessage;
import tda367.dominion.commons.messages.ConnectionMessage;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.commons.messages.PlayerUpdateMessage;
import tda367.dominion.commons.messages.RoomMessage;
import tda367.dominion.commons.network.NetworkCommon;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class NetworkHandler {
	private static Server server;

	/**
	 * Creates and returns a fresh instance of the server.
	 */
	public NetworkHandler() {
		server = new Server() {
			protected Connection newConnection() {
				return new GameConnection();
			}
		};
		server.start();
		NetworkCommon.register(server);

		try {
			server.bind(NetworkCommon.TCPPORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	protected static void sendRoomList(Connection c) {
		RoomMessage rmsg = new RoomMessage();
		// rmsg.setRooms(roomHandler.getRoomsAsString());
		// print(roomHandler.getRoomsAsString()[0][1]);
		c.sendTCP(rmsg);
	}

	// PlayerUpdateMessage pm = new PlayerUpdateMessage();
	// pm.setActions(2);
	// pm.setBuys(1);
	// pm.setMoney(5);
	// c.sendTCP(pm);
	//
	// CardUpdateMessage cm = new CardUpdateMessage();
	// ArrayList<String> l = new ArrayList<String>();
	// l.add("Village");
	// l.add("Gold");
	// cm.setHand(l);
	// cm.setInPlay(l);
	// cm.setDiscard(null);
	// cm.setDeckSize(10);
	// c.sendTCP(cm);

}