package tda367.dominion.cards;

import tda367.dominion.model.CardInfoHandler;
import tda367.dominion.model.Player;

public class Adventurer implements ICard {
	public static void play(Player player){
		CardInfoHandler cardInfoHandler = CardInfoHandler.getInstance();
		int treasuresRevealed = 0;
		while(treasuresRevealed <2){
			if(cardInfoHandler.getCardType(player.revealTopOfDeck()).equals("Treasure")){
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