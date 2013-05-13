package tda367.dominion.client.controller;

import tda367.dominion.client.model.ClientModel;
import tda367.dominion.client.view.MainView;
import tda367.dominion.commons.listener.ViewEvent;
import tda367.dominion.commons.listener.ViewListener;
import tda367.dominion.commons.messages.CardUpdateMessage;
import tda367.dominion.commons.messages.CreateBoolMessage;
import tda367.dominion.commons.messages.PlayerUpdateMessage;
import tda367.dominion.commons.messages.RoomMessage;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ClientController {
	private ClientModel model;
	private MainView view;
	
	public ClientController() {
		this.view = new MainView();
		//Start view in new thread
		(new Thread(view)).start();
		this.model = new ClientModel();
		model.addListener(new NetworkListener());
		while(view.getCurrentStateID() != MainView.MAINMENUSTATE){
			//Waiting for the game to be launched
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		view.addCardListener(new RoomListener());
		System.out.println("CardListener added");
		
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

	private void setRoomData(String[][] data) {
//		model.setRoomData(data);
	}
	
	// Listener classes
	class NetworkListener extends Listener {
		
		@Override
		public void connected(Connection c) {
			System.out.println("Connected, it works");
		}
		
		@Override
		public void disconnected(Connection c) {
			System.out.println("Disconnected");
		}
		
		@Override
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
				PlayerUpdateMessage o = (PlayerUpdateMessage)object;
				view.updatePlayer(o.getActions(), o.getBuys(), o.getMoney());
			}
			
			if (object instanceof CardUpdateMessage) {
				System.out.println("Update Cards");
				CardUpdateMessage o = (CardUpdateMessage)object;
				view.updateCards(o.getHand(), o.getInPlay(), o.getDiscard(), o.getDeckSize());
			}
		}

	}
	
	// TODO: Replace with lobby framework
	class RoomListener implements ViewListener {
		public void run(ViewEvent e) {
			model.searchForGame();
		}
	}
	
	// Will be activated every time a card is chosen
	class CardListener implements ViewListener {
		public void run(ViewEvent e) {
			System.out.println("Play " + e.getText());
			model.playCard(e.getText());
		}
	}
	
	class SupplyListener implements ViewListener {
		public void run(ViewEvent e) {
			model.supplyCard(e.getText());
		}
	}
	
	class BoolListener implements ViewListener {
		public void run(ViewEvent e) {
			model.boolMessage(e.getBool());
		}
	}
}
