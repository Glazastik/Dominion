package tda367.dominion.server.game;

import java.util.HashMap;
import java.util.LinkedList;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import tda367.dominion.commons.messages.*;
import tda367.dominion.server.network.NetworkHandler;

/**
 * @author Group 28
 * Master class for one instance of a game.
 * 
 */
public class Dominion {
	// Player related
	private final LinkedList<Player> players;
	private int activePlayer;
	
	//TODO: hashmap that pairs a player to a controller/ networklistener : private final HashMap<,Player>
	private final Supply supply;
	private final CardRulesHandler cardRulesHandler;
	private NetworkHandler network;
	
	/**
	 * Constructs a fine game of Dominion!
	 * @param players list of players playing the game
	 * @param supply the supply depot for the piles
	 */
	public Dominion(LinkedList<Player> players){
		this.players = players;
		this.supply = new Supply(players.size());
		cardRulesHandler = new CardRulesHandler(players, supply);
		network = NetworkHandler.getInstance();
		network.addListener(new NetworkListener());
	}
	
	/**
	 * Returns a list of players (see {@link Player}) 
	 * that are playing the game.
	 * 
	 * @return a list containing the players
	 */
	public LinkedList<Player> getPlayers(){
		return this.players;
	}
	
	/**
	 * Returns the {@link Supply} used in the game.
	 * 
	 * @return the {@link Supply}
	 */
	public Supply getSupply(){
		return this.supply;
	}
	
	
	/**
	 * Returns the {@link CardRulesHandler} used in the game.
	 * 
	 * @return the {@link CardRulesHandler}
	 */
	public CardRulesHandler getCardRulesHandler(){
		return this.cardRulesHandler;
	}

	public HashMap<String, Integer> getSupplyInfo() {
		return supply.getCardsInSupply();
		
	}
	
	/**
	 * A class that listens for game specific requests. 
	 */
	class NetworkListener extends Listener {
		
		@Override
		public void received(Connection c, Object object) {
			if (object instanceof LocatedCardMessage) {
				
			}
			
			if (object instanceof BoolMessage) {
				
			}
			
			if (object instanceof DoneMessage) {
				
			}
		}
	}
}