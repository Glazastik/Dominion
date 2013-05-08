package tda367.dominion.client.controller;

import tda367.dominion.client.model.ClientModel;
import tda367.dominion.client.view.MainView;
import tda367.dominion.commons.messages.CardUpdateMessage;
import tda367.dominion.commons.messages.ConnectionMessage;
import tda367.dominion.commons.messages.CreateBoolMessage;
import tda367.dominion.commons.messages.PlayerUpdateMessage;
import tda367.dominion.commons.messages.RoomMessage;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ClientController {
	private ClientModel model;
	private MainView view;
	
	public ClientController(MainView view) {
		this.view = view;
	}

	public void setModel(ClientModel model) {
		this.model = model;
		model.addListener(new NetworkListener());
	}
	
	public void boolMessage(boolean bool) {
		model.boolMessage(bool);
	}

	public void joinRoom(int id){
		//TODO: Connection object?
//		ConnectionMessage cmsg = new ConnectionMessage();
//		cmsg.setName("Plebben");
//		cmsg.setRoomId("" + id);
//		c.sendTCP(cmsg);
	}
	
	public void playCard(String card) {
		model.playCard(card);
	}

	public void searchForGame() {
		model.searchForGame();
	}

	private void setRoomData(String[][] data) {
		model.setRoomData(data);
	}
	
	// Listener classes
	class NetworkListener extends Listener {
		public void connected() {
			System.out.println("Connected, it works");
		}
		
		public void disconnected() {
			System.out.println("Disconnected");
		}
		
		public void received (Connection connection, Object object) {
			System.out.println("Received \"" + object.getClass().getName()
					+ "\" from server.");

			if (object instanceof RoomMessage) {
				System.out.println("Update room list");
				RoomMessage rmsg = (RoomMessage) object;
				setRoomData(rmsg.getRooms());
			}

			if (object instanceof CreateBoolMessage) {
				
			}
			
			if (object instanceof PlayerUpdateMessage) {
				System.out.println("Update Stats");
				model.updateStat((PlayerUpdateMessage) object);
			}
			
			if (object instanceof CardUpdateMessage) {
				System.out.println("Update Cards");
				model.updateCards((CardUpdateMessage) object);
			}
		}

	}
}
