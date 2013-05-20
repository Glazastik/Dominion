package tda367.dominion.server.game;

import java.util.HashMap;
import java.util.LinkedList;

import tda367.dominion.commons.messages.Message;
import tda367.dominion.commons.messages.SetupMessage;
import tda367.dominion.commons.messages.SupplyMessage;
import tda367.dominion.commons.messages.TurnMessage;
import tda367.dominion.commons.messages.TurnMessage.Phase;
import tda367.dominion.server.network.NetworkHandler;

/**
 * @author Group 28 Master class for one instance of a game.
 * 
 */
public class Dominion {
	// Player related
	private final LinkedList<Player> players;
	private TurnHandler turnHandler;

	private final Supply supply;
	private final CardRulesHandler cardRulesHandler;
	private NetworkHandler network;

	/**
	 * Constructs a fine game of Dominion!
	 * 
	 * @param players
	 *            list of players playing the game
	 * @param supply
	 *            the supply depot for the piles
	 */
	public Dominion(LinkedList<Player> players) {
		this.players = players;
		this.supply = new Supply(players.size());
		cardRulesHandler = new CardRulesHandler(players, supply);
		network = NetworkHandler.getInstance();
		turnHandler = new TurnHandler(getPlayerIDs());
		init();
//		startGame();
	}

	private void startGame() {
		turnHandler.startGame();
		while(!turnHandler.isGameOver()){
			//TODO: Main code goes here
		}
	}

	/**
	 * Initialize the game, send messages to all involved players.
	 */
	private void init() {
		SetupMessage msg = new SetupMessage();
		SupplyMessage smsg = new SupplyMessage();

		smsg.setSupply(supply.getCardsInSupply());

		String[] s = new String[players.size()];

		for (int i = 0; i < players.size(); i++) {
			s[i] = players.get(i).getName();
		}

		msg.setPlayers(s);
		msg.setSupply(smsg);

		sendToAll(msg);

		for (Player p : players) {
			p.updateCards();
			p.updateStats();
		}
		
		
	}
	
	private int[] getPlayerIDs() {
		int[] ids = new int[players.size()]; 
		for(int i = 0; i < ids.length; i++){
			ids[i] = players.get(i).getID();
		}
		return ids;
	}

	private void requestActiveActions() {
		TurnMessage msg = new TurnMessage();
		msg.setPhase(Phase.ACTION);
		getActivePlayer().send(msg);
		
	}

	private Player getActivePlayer() {
		int id = turnHandler.getActivePlayer();
		for(Player p: players){
			if(p.getID() == id){
				return p;
			}
		}
		return null;
	}

	/**
	 * Sends a particular message to all connected players.
	 * 
	 * @param msg
	 */
	private void sendToAll(Message msg) {
		for (Player p : players) {
			network.sendMessage(p.getID(), msg);
		}
	}

	/**
	 * Returns a list of players (see {@link Player}) that are playing the game.
	 * 
	 * @return a list containing the players
	 */
	public LinkedList<Player> getPlayers() {
		return this.players;
	}

	/**
	 * Returns the {@link Supply} used in the game.
	 * 
	 * @return the {@link Supply}
	 */
	public Supply getSupply() {
		return this.supply;
	}

	/**
	 * Returns the {@link CardRulesHandler} used in the game.
	 * 
	 * @return the {@link CardRulesHandler}
	 */
	public CardRulesHandler getCardRulesHandler() {
		return this.cardRulesHandler;
	}

	/**
	 * Returns a HashMap of the current cards in supply & their amounts.
	 * 
	 * @return
	 */
	public HashMap<String, Integer> getSupplyInfo() {
		return supply.getCardsInSupply();

	}
}