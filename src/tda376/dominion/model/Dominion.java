package tda376.dominion.model;

import java.util.LinkedList;

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
	 * Returns the {@link GainingHandler} used in the game.
	 * 
	 * @return the {@link GainingHandler}
	 */
	public GainingHandler getGainingHandler(){
		return this.gainingHandler;
	}
	
	/**
	 * Returns the {@link CardRulesHandler} used in the game.
	 * 
	 * @return the {@link CardRulesHandler}
	 */
	public CardRulesHandler getCardRulesHandler(){
		return this.cardRulesHandler;
	}
}