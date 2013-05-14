package tda367.dominion.client.controller;

import tda367.dominion.client.model.ClientModel;
import tda367.dominion.client.model.Settings;
import tda367.dominion.client.view.MainView;
import tda367.dominion.commons.listener.GameEvent;
import tda367.dominion.commons.listener.GameListener;
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
		model.searchForGame();
		while(view.getCurrentStateID() != Settings.MAINMENUSTATE){
			//Waiting for the game to be launched
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		view.addCardListener(new CardListener());
		view.addUpdateRoomListener(new UpdateRoomListener());
		view.addHostListener(new HostRoomListener());
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

			if (object instanceof RoomMessage) {
				RoomMessage rmsg = (RoomMessage) object;
				view.updateRoomData(rmsg.getRooms());
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
	/**
	 * A Listener that requests a new list of rooms from the server.
	 */
	class UpdateRoomListener implements GameListener {
		public void run(GameEvent e) {
			model.searchForGame();
		}
	}
	
	class JoinRoomListener implements GameListener {
		public void run(GameEvent e) {
			model.joinRoom(e.getInt());
		}
	}
	
	class HostRoomListener implements GameListener {
		public void run(GameEvent e) {
			model.hostRoom(e.getInt());
		}
	}
	
	// Will be activated every time a card is chosen
	class CardListener implements GameListener {
		public void run(GameEvent e) {
			System.out.println("Play " + e.getText());
			model.playCard(e.getText());
		}
	}
	
	class SupplyListener implements GameListener {
		public void run(GameEvent e) {
			model.supplyCard(e.getText());
		}
	}
	
	class BoolListener implements GameListener {
		public void run(GameEvent e) {
			model.boolMessage(e.getBool());
		}
	}
}
