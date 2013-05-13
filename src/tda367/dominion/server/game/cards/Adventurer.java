package tda367.dominion.server.game.cards;

import tda367.dominion.server.game.CardInfoHandler;
import tda367.dominion.server.game.Player;

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