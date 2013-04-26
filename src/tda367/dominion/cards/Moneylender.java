package tda367.dominion.cards;

import tda367.dominion.model.Player;

public class Moneylender {
	public static void play(Player player){
		if(player.getHand().contains("Copper")){
			player.trashCard("Copper");
		}
	}
}
