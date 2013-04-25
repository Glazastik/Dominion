package tda367.dominion.server;

import java.io.IOException;

import tda367.dominion.controller.GameRoom;
import tda367.dominion.messages.ConnectionMessage;
import tda367.dominion.messages.RoomMessage;

import com.esotericsoftware.kryonet.Connection;
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
		roomHandler.createRoom(new GameRoom(null));
		server = new Server();
		server.start();
		NetworkCommon.register(server);
		

		server.addListener(new Listener() {
			public void connected(Connection c){
				print("Received connection from " + c.getRemoteAddressTCP());
				sendRoomList(c);
				
			}
			
			public void received (Connection c, Object object) {
				
				print("Received stuff");
				if(object instanceof ConnectionMessage){
					print("Message!");
				} else {
					print("Classname: " + object.getClass());
					print(object.toString());
				}
			}
			
			public void disconnected(Connection c){
				print(c.getID() + " disconnected");
			}
		});
		
		try {
			server.bind(NetworkCommon.TCPPORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return server;

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