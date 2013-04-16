package tda376.dominion.cards;

import tda376.dominion.model.CardInfoHandler;
import tda376.dominion.model.Player;

public class Adventurer implements ICard {
	public static void play(Player player){
		CardInfoHandler cardInfoHandler = CardInfoHandler.getInstance();
		if(cardInfoHandler.getCardType(player.revealTopOfDeck()) == "Treasure"){
			
		}
	}
}
