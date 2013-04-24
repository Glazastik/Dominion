package tda367.dominion.model;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * A class designed to help players gain card, which means
 * adding it to their discard pile.
 * 
 * @author Grupp 28
 *
 */
public class GainingHandler {

	private final Supply supply;
	private final CardInfoHandler cardInfoHandler;
	
	/**
	 * Creates a new GainingHandler with a supply to gain cards 
	 * from and a {@link CardInfoHandler}.
	 * 
	 * @param supply the {@link Supply} to be gained from
	 */
	public GainingHandler(Supply supply) {
		// TODO Should probably be renamed
		this.supply = supply;
		cardInfoHandler = CardInfoHandler.getInstance();
	}
	public void playerBuyCard(Player player, String cardName){
		//TODO: Player player is to be removed when networking is properly set up.
		//The model should handle that on it's own
		if(supply.isAvailable(cardName)){
				if(cardInfoHandler.getCardValue(cardName)<=player.getMoney() && player.getBuys()>0){
				player.decreaseMoney(cardInfoHandler.getCardValue(cardName));
				player.decreaseBuy(1);
				player.gain(supply.take(cardName));
			}
		}
	}
	/**
	 * A method used to give a player any active card
	 * @param player the player 
	 * @param cardName the card
	 */
	public void playerGainCard(Player player, String cardName){
		if(supply.isAvailable(cardName)){
			player.gain(supply.take(cardName));
		}
	}
	/**
	 * A method used to give a player any active card to hand
	 * @param player the player 
	 * @param cardName the card
	 */
	public void playerGainCardToHand(Player player, String cardName){
		HashMap<String,Integer> cards = supply.getCardsInSupply();
		if(cards.containsKey(cardName)){
			if(cards.get(cardName)>0){
				player.addToHand((supply.take(cardName)));
			}
		}
	}

}
