package tda367.dominion.cards;

import tda367.dominion.model.Player;

public class Laboratory implements ICard {
	public static void play(Player player){
		player.draw(2);
		player.increaseActions(1);
	}
}
