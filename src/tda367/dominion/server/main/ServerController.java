package tda367.dominion.server.main;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.messages.ConnectionMessage;
import tda367.dominion.commons.messages.RoomMessage;
import tda367.dominion.commons.messages.RoomUpdateMessage;
import tda367.dominion.server.network.NetworkHandler;
import tda367.dominion.server.view.ServerFrame;

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
//			sendRoomList(c);
		}

		@Override
		public void received(Connection c, Object object) {
			
			if (object instanceof ConnectionMessage) {
				print(c.getRemoteAddressTCP()
						+ " wants to connect to a room.");
//				ConnectionMessage cmsg = (ConnectionMessage) object;
//				connectPlayer(gc, cmsg);
			} else if (object instanceof RoomUpdateMessage) {
				// TODO: To stop it from printing these.
				print("Updating room list for client");
				RoomMessage msg = new RoomMessage();
				msg.setRooms(roomHandler.getRoomsAsString());
				network.sendMessage(c.getID(), msg);
			} else if (object instanceof CardMessage) {
				// TODO: Play the card
//				print("Player played: " + ((CardMessage) object).getCard());
			} else {
				print("Classname: " + object.getClass());
				print(object.toString());
			}
		}

		@Override
		public void disconnected(Connection c) {
			print("Player " + c.getID() + " disconnected");
//			GameConnection gc = (GameConnection) c;
//			roomHandler.kickConnection(gc);
//			print(c.getID() + " disconnected and kicked");
		}
	}
	
	private void print(String s) {
		view.print(s);
	}
}