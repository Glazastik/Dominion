package tda367.dominion.server.cards;

import tda367.dominion.server.game.Player;
/**
 * A class used to represent the card Moneylender
 * @author christoffer
 *
 */
public class Moneylender {
	/**
	 * Calling this method affects a player as if they played the card Moneylender.
	 * For simplicitys sake the player has no choice in which copper he/she trashes. (It should not matter)
	 * @param player the player that played the card
	 */
	public static void play(Player player){
		if(player.getHand().contains("Copper")){
			player.trashCard("Copper");
			player.increaseMoney(3);
		}
	}
}
