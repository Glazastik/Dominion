package tda376.dominion.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Supply contains many piles that players can buy cards from
 * @author Group 28
 *
 */

public class Supply {
	//TODO: Further functionality might be added
	private CardInfoHandler cardInfoHandler;
	private final HashMap<String,Integer> cards;
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
		cards.put("Copper", 60 - (amountOfPlayers*7));
		cards.put("Silver", 40);
		cards.put("Gold", 30);
		//Set the amount of victorycards available depending on the amount of players.
		//Further options are required to support 5 or 6 player games.
		if(amountOfPlayers==2){
			cards.put("Estate", 8);
			cards.put("Duchy", 8);
			cards.put("Province", 8);
		} else {
			cards.put("Estate", 12);
			cards.put("Duchy", 12);
			cards.put("Province", 12);
		}
		cards.put("Curse",((amountOfPlayers-1)*10));
		
		//Get the action cards and the gardens card
		LinkedList<String> actionCards = cardInfoHandler.getActionCards();
		actionCards.add("Gardens");
		
		//Generate 10 random numbers 
		int numbersGenerated = 0;
		int tempInt;
		LinkedList<Integer> numbers = new LinkedList<Integer>(); 
		Random generator = new Random();
		while(numbersGenerated<10){
			tempInt = generator.nextInt(actionCards.size());
			if(!numbers.contains(tempInt)){
				numbers.add(tempInt);
				numbersGenerated++;
			}
		}
		
		//Add the cards
		Iterator i = numbers.iterator();
		String tempCard;
		while(i.hasNext()){
			tempCard = actionCards.get((int) i.next());
			if(tempCard.equals("Gardens")){
				cards.put(tempCard, 8);
			} else {
				cards.put(tempCard, 12);
			}
		}
	}
	/**
	 * Returns the cards which are active this game.
	 * These are not the cards that are available for purchase but the ones that are active
	 * @return The active cards of the round
	 */
	public HashMap<String,Integer> getActiveCards(){
		return (HashMap<String, Integer>) cards.clone();
		
	}
	/**
	 * 
	 * @param cardName the card to take from supply
	 * @return the card, null if there were no cards left of the chosen type
	 */
	public String take(String cardName){
		int temp = cards.get(cardName);
		if(temp>0){
			temp--;
			cards.put(cardName, temp);
			return cardName;
		} else {
			return null;
		}
	}
	/**
	 * A method to determine if the game is over
	 * @return true if the game is over
	 */
	public boolean gameIsOver(){
		Set<String> cardKeys = cards.keySet();
		Iterator i = cardKeys.iterator();
		int pilesEmpty = 0;
		while(i.hasNext()){
			if(cards.get(i.next()) == 0){
				pilesEmpty++;
			}
		}
		return (pilesEmpty>= 3 || cards.get("Province") == 0);
	}
	//TODO: Possible method to add.
//	public HashMap<String,Integer> getGainableCards(int maxValue){
//		
//	}
}