package tda376.dominion.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Group 28
 * Master class for the game.
 * 
 */
public class Dominion {
	private final LinkedList<Player> players;
	//TODO: hashmap that pairs a player to a controller/ networklistener : private final HashMap<,Player>
	private final Supply supply;
	private final GainingHandler gainingHandler;
	private final CardRulesHandler cardRulesHandler;
	/**
	 * Constructs a fine game of Dominion!
	 * @param players list of players playing the game
	 * @param supply the supply depot for the piles
	 */
	public Dominion(LinkedList<Player> players){
		this.players = players;
		this.supply = new Supply(players.size());
		gainingHandler = new GainingHandler(supply);
		cardRulesHandler = new CardRulesHandler(players, gainingHandler);
	}
	public LinkedList<Player> getPlayers(){
		return this.players;
	}
	public Supply getSupply(){
		return this.supply;
	}
	public GainingHandler getGainingHandler(){
		return this.gainingHandler;
	}
	public CardRulesHandler getCardRulesHandler(){
		return this.cardRulesHandler;
	}
}