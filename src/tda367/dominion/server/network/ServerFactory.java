package tda367.dominion.server.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.messages.CardUpdateMessage;
import tda367.dominion.commons.messages.ConnectionMessage;
import tda367.dominion.commons.messages.PlayerUpdateMessage;
import tda367.dominion.commons.messages.RoomMessage;
import tda367.dominion.commons.messages.SetupMessage;
import tda367.dominion.commons.network.NetworkCommon;
import tda367.dominion.server.view.ServerFrame;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.FrameworkMessage.KeepAlive;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class ServerFactory {
	private static Server server;
	private static RoomHandler roomHandler;

	/**
	 * Creates and returns a fresh instance of the server.
	 * 
	 * @return
	 */
	public static Server getInstance() {
		if (server != null) {
			return server;
		}

		roomHandler = new RoomHandler();
		roomHandler.createRoom("TestRoom");
		server = new Server() {
			public Connection newConnection() {
				return new GameConnection();
			}
		};
		server.start();
		NetworkCommon.register(server);

		server.addListener(new Listener() {
			public void connected(Connection c) {
				print("Received connection from " + c.getRemoteAddressTCP());
				sendRoomList(c);

			}

			public void received(Connection c, Object object) {
				GameConnection gc = (GameConnection) c;

				if (object instanceof ConnectionMessage) {
					print(c.getRemoteAddressTCP()
							+ " wants to connect to a room.");
					ConnectionMessage cmsg = (ConnectionMessage) object;
					connectPlayer(gc, cmsg);
				} else if (object instanceof KeepAlive) {
					// TODO: To stop it from printing these.
				} else if (object instanceof CardMessage) {
					// TODO: Play the card
					print("Player played: " + ((CardMessage) object).getCard());
				} else {
					print("Classname: " + object.getClass());
					print(object.toString());
				}
			}

			public void disconnected(Connection c) {
				GameConnection gc = (GameConnection) c;
				roomHandler.kickConnection(gc);
				print(c.getID() + " disconnected and kicked");
			}
		});

		try {
			server.bind(NetworkCommon.TCPPORT);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return server;

	}

	protected static void connectPlayer(GameConnection c, ConnectionMessage cmsg) {
		int id = Integer.parseInt(cmsg.getRoomId());
		c.setPlayerName(cmsg.getName());
		
		if (!roomHandler.addPlayer(c, id)){
			print("Couldn't add " + cmsg.getName() + " to room " + cmsg.getRoomId());
			return;
		}

		if(roomHandler.start(id)){
			setupGame(id);
		}
	}

	/**
	 * This method will hand notify all the players that the game has begun.
	 * @param id
	 */
	private static void setupGame(int id) {
		//TODO: Notify all players
		LinkedList<GameConnection> gcs = roomHandler.getPlayers(id);
		SetupMessage setupMsg = roomHandler.getSetupMessage(id);
		
		
		for(GameConnection gc: gcs){
			
		}
		
	}

	public static RoomHandler getRoomHandler() {
		return roomHandler;
	}

	protected static void sendRoomList(Connection c) {
		RoomMessage rmsg = new RoomMessage();
		rmsg.setRooms(roomHandler.getRoomsAsString());
		print(roomHandler.getRoomsAsString()[0][1]);
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

	private static void print(String s) {
		ServerFrame.getInstance().print(s);
	}

}