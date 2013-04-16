package tda376.dominion.cards;

import tda376.dominion.model.CardInfoHandler;
import tda376.dominion.model.Player;

public class Adventurer implements ICard {
	public static void play(Player player){
		CardInfoHandler cardInfoHandler = CardInfoHandler.getInstance();
		int treasuresRevealed = 0;
		while(treasuresRevealed <2){
			if(cardInfoHandler.getCardType(player.revealTopOfDeck()) == "Treasure"){
				player.draw();
				treasuresRevealed++;
			} else if (cardInfoHandler.getCardType(player.revealTopOfDeck()) == null){
				break;
			} else {
				player.setAsideTopOfDeck();
			}
		}
		player.putRevealedCardsInDiscard();
	}
}