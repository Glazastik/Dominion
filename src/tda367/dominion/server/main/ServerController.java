package tda367.dominion.server.main;

import java.util.LinkedList;

import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.messages.ConnectionMessage;
import tda367.dominion.commons.messages.RoomMessage;
import tda367.dominion.commons.messages.RoomUpdateMessage;
import tda367.dominion.commons.messages.SetupMessage;
import tda367.dominion.server.network.GameConnection;
import tda367.dominion.server.network.NetworkHandler;
import tda367.dominion.server.view.ServerFrame;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ServerController {
	private ServerFrame view;
	private NetworkHandler network;
	private RoomHandler roomHandler;

	public ServerController() {
		network = new NetworkHandler();
		view = ServerFrame.getInstance();
		roomHandler = new RoomHandler();
		network.addListener(new NetworkListener());
	}

	class NetworkListener extends Listener {

		@Override
		public void connected(Connection c) {
			print("Received connection from " + c.getRemoteAddressTCP());
		}

		@Override
		public void received(Connection c, Object object) {
			GameConnection gc = (GameConnection) c;
			if (object instanceof ConnectionMessage) {
				print(c.getRemoteAddressTCP() + " wants to connect to a room.");
				ConnectionMessage cmsg = (ConnectionMessage) object;
				connectPlayer(gc, cmsg.getRoomId(), cmsg.getName());
			} else if (object instanceof RoomUpdateMessage) {
				sendRoomList(c);
			} else if (object instanceof CardMessage) {
				// TODO: Play the card
				// print("Player played: " + ((CardMessage) object).getCard());
			} else {
				print("Classname: " + object.getClass());
				print(object.toString());
			}
		}

		@Override
		public void disconnected(Connection c) {
			GameConnection gc = (GameConnection) c;
			print("Player " + gc.getID() + " disconnected");
			 
			 roomHandler.kickConnection(gc);
			 print(c.getID() + " disconnected and kicked");
		}

		private void connectPlayer(GameConnection gc, int roomId, String name) {
			gc.setPlayerName(name);

			if (!roomHandler.addPlayer(gc, roomId)) {
				print("Couldn't add " + name + " to room "
						+ roomId);
				return;
			}

			if (roomHandler.start(roomId)) {
				setupGame(roomId);
			}
		}

		/**
		 * This method will hand notify all the players that the game has begun.
		 * 
		 * @param id
		 */
		private void setupGame(int id) {
			// TODO: Notify all players
			LinkedList<GameConnection> gcs = roomHandler.getPlayers(id);
			SetupMessage setupMsg = roomHandler.getSetupMessage(id);

			for (GameConnection gc : gcs) {
				gc.sendTCP(setupMsg);
			}

		}
		
		private void sendRoomList(Connection c) {
			print("Updating room list for client");
			RoomMessage msg = new RoomMessage();
			msg.setRooms(roomHandler.getRoomsAsString());
			network.sendMessage(c.getID(), msg);
		}
	}

	private void print(String s) {
		view.print(s);
	}
}