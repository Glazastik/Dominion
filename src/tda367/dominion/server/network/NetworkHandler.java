package tda367.dominion.server.network;

import java.io.IOException;
import java.util.ArrayList;

import tda367.dominion.commons.messages.CardUpdateMessage;
import tda367.dominion.commons.messages.ConnectionMessage;
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
		server = new Server();
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

	protected static void connectPlayer(GameConnection c, ConnectionMessage cmsg) {
		int id = Integer.parseInt(cmsg.getRoomId());
		c.setPlayerName(cmsg.getName());
		
//		if (!roomHandler.addPlayer(c, id)){
//			print("Couldn't add " + cmsg.getName() + " to room " + cmsg.getRoomId());
//			return;
//		}
//
//		if(roomHandler.start(id)){
//			setupGame(id);
//		}
	}

	/**
	 * This method will hand notify all the players that the game has begun.
	 * @param id
	 */
	private static void setupGame(int id) {
		//TODO: Notify all players
//		LinkedList<GameConnection> gcs = roomHandler.getPlayers(id);
//		SetupMessage setupMsg = roomHandler.getSetupMessage(id);
		
//		for(GameConnection gc: gcs){
//			gc.sendTCP(setupMsg);
//		}
		
	}

	protected static void sendRoomList(Connection c) {
		RoomMessage rmsg = new RoomMessage();
//		rmsg.setRooms(roomHandler.getRoomsAsString());
//		print(roomHandler.getRoomsAsString()[0][1]);
		c.sendTCP(rmsg);
		
		PlayerUpdateMessage pm = new PlayerUpdateMessage();
		pm.setActions(2);
		pm.setBuys(1);
		pm.setMoney(5);
		c.sendTCP(pm);
		
		CardUpdateMessage cm = new CardUpdateMessage();
		ArrayList<String> l = new ArrayList<String>();
		l.add("Village");
		l.add("Gold");
		cm.setHand(l);
		cm.setInPlay(l);
		cm.setDiscard(null);
		cm.setDeckSize(10);
		c.sendTCP(cm);
	}

}