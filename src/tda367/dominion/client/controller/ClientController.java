package tda367.dominion.client.controller;

import tda367.dominion.client.model.ClientModel;
import tda367.dominion.client.model.Settings;
import tda367.dominion.client.view.MainView;
import tda367.dominion.client.view.Transitions;
import tda367.dominion.commons.listener.GameEvent;
import tda367.dominion.commons.listener.GameListener;
import tda367.dominion.commons.messages.*;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 * The client's most powerful class.
 * Has access to pretty much everything and listens to the network.
 *  
 * @author Group 28
 */
public class ClientController {
	private ClientModel model;
	private MainView view;

	public ClientController() {
		this.view = new MainView();
		// Start view in new thread
		(new Thread(view)).start();
		this.model = new ClientModel();
		model.addListener(new NetworkListener());
		model.searchForGame();
		while (view.getCurrentStateID() != Settings.MAINMENUSTATE) {
			// Waiting for the game to be launched
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		view.addUpdateRoomListener(new UpdateRoomListener());
		view.addJoinListener(new JoinRoomListener());
		view.addHostListener(new HostRoomListener());
		view.addSupplyListener(new SupplyListener());
		view.addCardListener(new CardListener());
		view.addAdvanceListener(new AdvanceListener());
		view.addPlayAllListener(new PlayAllListener());
		view.addExitListener(new ExitListener());
		view.addSettingsListener(new SettingsListener());
		view.addDoneListener(new DoneListener());
		view.addBoolListener(new BoolListener());
		view.addBackListener(new BackListener());
	}

	// Listener classes
	class NetworkListener extends Listener {

		@Override
		public void received(Connection connection, Object object) {

			if (object instanceof RevealCardMessage) {
				RevealCardMessage rcm = (RevealCardMessage) object;
				view.setRevealedCard(rcm.getCard());
			}
			
			if (object instanceof RevealMultipleCardMessage) {
				RevealMultipleCardMessage rmcm = (RevealMultipleCardMessage) object;
				view.setRevealedCards(rmcm.getCards());
			}
			
			if (object instanceof RoomMessage) {
				RoomMessage rmsg = (RoomMessage) object;
				view.updateRoomData(rmsg.getRooms());
				
			}

			if (object instanceof CreateBoolMessage) {
				CreateBoolMessage cbm = (CreateBoolMessage) object;
				view.activateYesNoBox(cbm.getText());
			}
			
			if (object instanceof PlayerUpdateMessage) {
				PlayerUpdateMessage o = (PlayerUpdateMessage) object;
				view.updatePlayer(o.getActions(), o.getBuys(), o.getMoney());
			}

			if (object instanceof CardUpdateMessage) {
				CardUpdateMessage o = (CardUpdateMessage) object;
				view.updateCards(o.getHand(), o.getInPlay(), o.getDiscard(),
						o.getDeckSize());
			}
			
			if(object instanceof TipMessage){
				view.updateTip(((TipMessage) object).getMessage());
			}
			
			if(object instanceof LogMessage){
				view.updateLog(((LogMessage) object).getMessage());
			}

			if (object instanceof SetupMessage) {
				SetupMessage setup = (SetupMessage) object;
				SupplyMessage supply = setup.getSupply();
				Settings.inGame = true;
				view.updateSupply(supply.getSupply());
				view.enterState(Settings.INGAMESTATE);
				view.updatePlayersInfo(setup.getPlayers());
			}
			
			if (object instanceof SupplyMessage){
				SupplyMessage msg = (SupplyMessage) object;
				view.updateSupply(msg.getSupply());
			}

			if (object instanceof TurnMessage) {
				TurnMessage turn = (TurnMessage) object;
				String phase = turn.getPhase();
				String player = turn.getActive();
				if(player.equals(Settings.getName())){
					if (phase.equals("action")) {
						view.updatePhase("action");
						model.setPhase("action");

					} else if (phase.equals("buy")) {
						view.updatePhase("buy");
						model.setPhase("buy");

					} else if (phase.equals("cleanup")) {
						view.updatePhase("cleanup");
						model.setPhase("cleanup");
					}
				} else {
					view.updatePhase("<"+player+">");
					model.setPhase("");
				}
				
				
				
			}
			
			if (object instanceof EndMessage) {
				EndMessage egs = ((EndMessage)object);
				view.setupEndState(egs.getNames(), egs.getScores());
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
			model.connect();
		}
	}

	class JoinRoomListener implements GameListener {
		public void run(GameEvent e) {
			model.joinRoom(e.getInt());
			model.connect();
		}
	}

	class HostRoomListener implements GameListener {
		public void run(GameEvent e) {
			model.hostRoom(e.getInt());
			model.connect();
		}
	}

	// Will be activated every time a card is chosen
	class CardListener implements GameListener {
		public void run(GameEvent e) {
			model.playCard(e.getText());
		}
	}

	class AdvanceListener implements GameListener {
		public void run(GameEvent e) {

			model.nextPhase();
		}
	}

	class SupplyListener implements GameListener {
		public void run(GameEvent e) {
			model.supplyCard(e.getText());
		}
	}
	
	class PlayAllListener implements GameListener {
		public void run(GameEvent e) {
			model.playAll();
		}
	}
	
	class BoolListener implements GameListener {
		public void run(GameEvent e) {
			model.boolMessage(e.getBool());
		}
	}
	
	class ExitListener implements GameListener {
		public void run(GameEvent e) {
			MainView.exit();
		}
	}
	
	class SettingsListener implements GameListener {
		public void run(GameEvent e) {
			MainView.updateSettings();
		}
	}
	
	class DoneListener implements GameListener {
		public void run(GameEvent e) {
			model.doneCard();
		}
	}
	
	class BackListener implements GameListener {
		public void run(GameEvent e) {
			view.enterState(Settings.MAINMENUSTATE, null, Transitions.createNewHorizontalSplitTransition());
		}
	}
}
