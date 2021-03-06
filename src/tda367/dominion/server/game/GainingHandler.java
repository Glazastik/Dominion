package tda367.dominion.server.game;

import java.util.HashMap;

import tda367.dominion.commons.game.CardInfoHandler;

/**
 * A class designed to help players gain card, which means adding it to their
 * discard pile.
 * 
 * @author Grupp 28
 * 
 */
public class GainingHandler {

	private final Supply supply;
	private final CardInfoHandler cardInfoHandler;
	private final TurnHandler turnHandler;

	/**
	 * Creates a new GainingHandler with a supply to gain cards from and a
	 * {@link CardInfoHandler}.
	 * 
	 * @param supply
	 *            the {@link Supply} to be gained from
	 */
	public GainingHandler(Supply supply, TurnHandler turnHandler) {
		// TODO Should probably be renamed
		this.supply = supply;
		this.turnHandler = turnHandler;
		cardInfoHandler = CardInfoHandler.getInstance();
	}

	public GainingHandler(Supply supply) {
		this(supply, new TurnHandler(4));
	}

	public void playerBuyCard(Player player, String cardName) {
		// TODO: Player player is to be removed when networking is properly set
		// up.
		// The model should handle that on it's own
		if (supply.isAvailable(cardName)) {
			if (cardInfoHandler.getCardValue(cardName) <= player.getMoney()
					&& player.getBuys() > 0) {
				player.decreaseMoney(cardInfoHandler.getCardValue(cardName));
				player.decreaseBuy(1);
				player.gain(supply.take(cardName));
				turnHandler.setBought(true);
				
			}
		}
	}

	/**
	 * A method used to give a player any active card
	 * 
	 * @param player
	 *            the player
	 * @param cardName
	 *            the card
	 */
	public void playerGainCard(Player player, String cardName) {
		if (supply.isAvailable(cardName)) {
			player.gain(supply.take(cardName));
		}
	}

	/**
	 * A method used to give a player any active card to hand
	 * 
	 * @param player
	 *            the player
	 * @param cardName
	 *            the card
	 */
	public void playerGainCardToHand(Player player, String cardName) {
		HashMap<String, Integer> cards = supply.getCardsInSupply();
		if (cards.containsKey(cardName)) {
			if (cards.get(cardName) > 0) {
				player.addToHand((supply.take(cardName)));
			}
		}
	}

	public boolean isCardGainable(String cardName, int maxValue) {
		HashMap<String, Integer> gainableCards = supply
				.getGainableCards(maxValue);
		if (gainableCards.containsKey(cardName)) {
			return (gainableCards.get(cardName)) > 0;
		}
		return false;
	}

	public boolean isCardGainable(String cardName) {
		HashMap<String, Integer> gainableCards = supply.getCardsInSupply();
		if (gainableCards.containsKey(cardName)) {
			return (gainableCards.get(cardName)) > 0;
		}
		return false;

	}
}
