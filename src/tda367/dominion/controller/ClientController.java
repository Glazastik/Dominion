package tda367.dominion.controller;

import tda367.dominion.client.ClientModel;
import tda367.dominion.messages.ConnectionMessage;
import tda367.dominion.messages.RoomMessage;

import com.esotericsoftware.kryonet.*;

public class ClientController extends Listener {
	private ClientModel model;
	
	public void setModel(ClientModel model) {
		this.model = model;
	}
	
	public void connected(Connection c) {
		System.out.println("Connected to the server on: "
				+ c.getRemoteAddressTCP());
	}

	public void received(Connection c, Object object) {
		System.out.println("Received \"" + object.getClass().getName()
				+ "\" from server.");

		if (object instanceof RoomMessage) {
			RoomMessage rmsg = (RoomMessage) object;
			
			for(String[] s: rmsg.getRooms()){
				System.out.println("Room:");
				System.out.println("Name: " + s[0] + " \n Slots: "+ s[1] + "\n");
			}
		}

	}

	public void disconnected(Connection c) {
		model.disconnected();
	}
}
