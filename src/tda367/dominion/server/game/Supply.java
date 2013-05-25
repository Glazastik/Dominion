package tda367.dominion.server.game;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

/**
 * Supply contains many piles that players can buy cards from
 * 
 * @author Group 28
 * 
 */

public class Supply {
	// TODO: Further functionality might be added
	private CardInfoHandler cardInfoHandler;
	private final HashMap<String, Integer> cardsInSupply;

	public Supply(int amountOfPlayers) {
		cardInfoHandler = CardInfoHandler.getInstance();
		cardsInSupply = new HashMap<String, Integer>();
		initializeSupply(amountOfPlayers);
	}

	/**
	 * A method to set the initial values of the cards in supply
	 * 
	 * @param amountOfPlayers
	 *            amount of players in the game
	 */
	private void initializeSupply(int amountOfPlayers) {
		cardsInSupply.put("Copper", 60 - (amountOfPlayers * 7));
		cardsInSupply.put("Silver", 40);
		cardsInSupply.put("Gold", 30);
		// Set the amount of victorycards available depending on the amount of
		// players.
		// Further options are required to support 5 or 6 player games.
		if (amountOfPlayers == 2) {
			cardsInSupply.put("Estate", 8);
			cardsInSupply.put("Duchy", 8);
			cardsInSupply.put("Province", 8);
		} else {
			cardsInSupply.put("Estate", 12);
			cardsInSupply.put("Duchy", 12);
			cardsInSupply.put("Province", 12);
		}
		cardsInSupply.put("Curse", ((amountOfPlayers - 1) * 10));

		// Get the action cards and the gardens card
		LinkedList<String> actionCards = cardInfoHandler.getActionCards();
		actionCards.add("Gardens");

		// Generate 10 random numbers
		int tempInt;
		LinkedList<Integer> numbers = new LinkedList<Integer>();
		Random generator = new Random();
		while (numbers.size() < 10) {
			tempInt = generator.nextInt(actionCards.size());
			if (!numbers.contains(tempInt)) {
				numbers.add(tempInt);
			}
		}

		// Add the cards
		String tempCard;
		for (Integer i : numbers) {
			tempCard = actionCards.get(i);
			if (tempCard.equals("Gardens")) {
				if (amountOfPlayers > 2) {
					cardsInSupply.put(tempCard, 12);
				} else {
					cardsInSupply.put(tempCard, 8);
				}
			} else {
				cardsInSupply.put(tempCard, 10);
			}
		}
	}

	/**
	 * Returns the cards which are active this game. These are not the cards
	 * that are available for purchase but the ones that are active
	 * 
	 * @return The active cards of the round
	 */
	public HashMap<String, Integer> getCardsInSupply() {
		return (HashMap<String, Integer>) cardsInSupply.clone();

	}

	/**
	 * 
	 * @param cardName
	 *            the card to take from supply
	 * @return the card, null if there were no cards left of the chosen type
	 */
	public String take(String cardName) {
		if (cardsInSupply.containsKey(cardName)) {
			int temp = cardsInSupply.get(cardName);
			if (temp > 0) {
				temp--;
				cardsInSupply.put(cardName, temp);
				return cardName;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * A method to determine if the game is over
	 * 
	 * @return true if the game is over
	 */
	public boolean gameIsOver() {
		Set<String> cardKeys = cardsInSupply.keySet();
		Iterator i = cardKeys.iterator();
		int pilesEmpty = 3;
		while (i.hasNext()) {
			if (cardsInSupply.get(i.next()) == 0) {
				pilesEmpty++;
			}
		}
		return (pilesEmpty >= 3 || cardsInSupply.get("Province") == 0);
	}

	public boolean isAvailable(String card) {
		if (cardsInSupply.containsKey(card)) {
			return (cardsInSupply.get(card) > 0);
		} else {
			return false;
		}
	}

	/**
	 * Returns the cards that can be gained within the provided maximum value.
	 * 
	 * @param maxValue
	 * @return a HashMap with the cards and the amount of them in the supply
	 */
	public HashMap<String, Integer> getGainableCards(int maxValue) {
		HashMap<String, Integer> toSender = new HashMap<String, Integer>();
		CardInfoHandler cih = CardInfoHandler.getInstance();

		Set<String> cardKeys = cardsInSupply.keySet();

		for (String card : cardKeys) {
			if (cih.getCardValue(card) <= maxValue) {
				toSender.put(card, cardsInSupply.get(card));
			}
		}

		return toSender;
	}
}