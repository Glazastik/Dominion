package tda376.dominion.model;

import java.util.List;

/**
 * @author Group 28
 * Master class for the game.
 * 
 */
public class Dominion {
	private final List<Player> players;
	private final Supply supply;
	
	/**
	 * Constructs a fine game of Dominion!
	 * @param players list of players playing the game
	 * @param supply the supply depot for the piles
	 */
	public Dominion(List<Player> players, Supply supply){
		this.players = players;
		this.supply = supply;
	}
	
	
	
}
