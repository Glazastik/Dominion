package tda367.dominion.controller;

import tda367.dominion.messages.*;
import tda367.dominion.model.ClientModel;

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
				System.out.println("RoomID:" + s[2]);
				System.out.println("Name: " + s[0] + " \n Slots: "+ s[1] + "\n");
			}
			
			ConnectionMessage cmsg = new ConnectionMessage();
			cmsg.setName("Plebben");
			cmsg.setRoomId("0");
		}

	}
	
	public void searchForGame() {
		model.searchForGame();
	}

	public void disconnected(Connection c) {
		model.disconnected();
	}
}
