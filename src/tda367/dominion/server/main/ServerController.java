package tda367.dominion.server.main;

import java.util.LinkedList;

import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.messages.ConnectionMessage;
import tda367.dominion.commons.messages.RoomHostMessage;
import tda367.dominion.commons.messages.RoomMessage;
import tda367.dominion.commons.messages.RoomUpdateMessage;
import tda367.dominion.commons.messages.SetupMessage;
import tda367.dominion.server.network.GameConnection;
import tda367.dominion.server.network.NetworkHandler;
import tda367.dominion.server.view.ServerFrame;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.FrameworkMessage.KeepAlive;
import com.esotericsoftware.kryonet.Listener;

public class ServerController {
	private ServerFrame view;
	private NetworkHandler network;
	private RoomHandler roomHandler;

	public ServerController() {
		network = NetworkHandler.getInstance();
		view = ServerFrame.getInstance();
		roomHandler = new RoomHandler();
		network.addListener(new NetworkListener());
	}

	class NetworkListener extends Listener {

		@Override
		public void connected(Connection c) {
			print("Received connection from " + c.getRemoteAddressTCP());
			//Settings for idleness
			c.setKeepAliveTCP(60000);
			c.setTimeout(0);
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

			} else if (object instanceof RoomHostMessage) {
				hostRoom(gc, ((RoomHostMessage) object).getName());
				sendRoomList(c);
				
			} else if (object instanceof CardMessage) {
				// TODO: Play the card
				// print("Player played: " + ((CardMessage) object).getCard());
			} else if (object instanceof KeepAlive) {
				// Don't say anything you stupid server
			} else {
				//If object is unknown/unhandled
				print("Classname: " + object.getClass());
				print(object.toString());
			}
		}

		private void hostRoom(GameConnection gc, String name) {
			
			roomHandler.createRoom(name);
			int id = roomHandler.getRoomByName(name).getID();
			connectPlayer(gc, id, name);
		}

		@Override
		public void disconnected(Connection c) {
			GameConnection gc = (GameConnection) c;
			roomHandler.kickConnection(gc);
			print(gc.getPlayerName() + " disconnected and kicked");
		}

		private void connectPlayer(GameConnection gc, int roomId, String name) {
			gc.setPlayerName(name);
			roomHandler.addPlayer(gc, roomId);
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