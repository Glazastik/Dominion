package tda367.dominion.server.cards;

import tda367.dominion.server.model.CardInfoHandler;
import tda367.dominion.server.model.Player;

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