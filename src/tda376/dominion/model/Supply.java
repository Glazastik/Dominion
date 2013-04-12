package tda376.dominion.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Supply contains many piles that players can buy cards from
 * @author Group 28
 *
 */

public class Supply {
	private CardInfoHandler cardInfoHandler;
	HashMap<String,Integer> cards;
	public Supply(int amountOfPlayers){
		cardInfoHandler = CardInfoHandler.getInstance();
		cards = new HashMap<String,Integer>();
		initializeSupply(amountOfPlayers);
	}
	/**
	 * A method to set the initial values of the cards in supply
	 * @param amountOfPlayers amount of players in the game
	 */
	private void initializeSupply(int amountOfPlayers){
		//TODO: Correct amount of victory cards depending on the amount of players
		//TODO: Randomizing and selecting action cards
		cards.put("Copper", 60);
		cards.put("Silver", 40);
		cards.put("Silver", 30);
		//Set the amount of victorycards available depending on the amount of players.
		//Further options are required to support 5 or 6 player games.
		if(amountOfPlayers==2){
			cards.put("Estate", 14);
			cards.put("Duchy", 8);
			cards.put("Province", 8);
		} else {
			if(amountOfPlayers==3){
				cards.put("Estate", 21);
			} else {
				cards.put("Estate", 24);
			}
			cards.put("Duchy", 12);
			cards.put("Province", 12);
		}
		cards.put("Curse",((amountOfPlayers-1)*10));
		LinkedList<String> temp = cardInfoHandler.getActionCards();
		//TODO: Selecting random cards and adding them to a cards
	}
}