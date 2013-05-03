package tda367.dominion.server.network;

import java.io.IOException;

import tda367.dominion.commons.messages.ConnectionMessage;
import tda367.dominion.commons.messages.RoomMessage;
import tda367.dominion.commons.network.NetworkCommon;
import tda367.dominion.server.model.GameRoom;
import tda367.dominion.server.model.RoomHandler;
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
		roomHandler.createRoom(new GameRoom());
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

	}

	private static void print(String s) {
		ServerFrame.getInstance().print(s);
	}

}