package tda367.dominion.controller;

import tda367.dominion.messages.*;
import tda367.dominion.model.ClientModel;

import com.esotericsoftware.kryonet.*;

public class ClientController extends Listener {
	private ClientModel model;
	private Connection c;

	public void setModel(ClientModel model) {
		this.model = model;
	}

	// TODO: remove connection object
	public void connected(Connection c) {
		System.out.println("Connected to the server on: "
				+ c.getRemoteAddressTCP());
	}

	// TODO: remove connection object
	public void received(Connection c, Object object) {
		System.out.println("Received \"" + object.getClass().getName()
				+ "\" from server.");

		if (object instanceof RoomMessage) {
			RoomMessage rmsg = (RoomMessage) object;
			setRoomData(rmsg.getRooms());
			this.c = c;
		}

		if (object instanceof CreateBoolMessage) {

		}
	}
	
	public void joinRoom(int id){
		//TODO: Connection object?
		ConnectionMessage cmsg = new ConnectionMessage();
		cmsg.setName("Plebben");
		cmsg.setRoomId(id+"");
		c.sendTCP(cmsg);
	}

	public void boolAnswer(boolean bool) {
		model.boolAnswer(bool);
	}

	public void searchForGame() {
		model.searchForGame();
	}

	public void disconnected(Connection c) {
		model.disconnected();
	}

	private void setRoomData(String[][] data) {
		model.setRoomData(data);
	}
}
