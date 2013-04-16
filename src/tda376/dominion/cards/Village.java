package tda376.dominion.cards;

import tda376.dominion.model.Player;

public class Village implements ICard {
	public static void play(Player player){
		player.draw();
		player.increaseActions(2);	
	}
}
