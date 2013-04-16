package tda376.dominion.cards;

import tda376.dominion.model.Player;

public class Laboratory implements ICard {
	public static void play(Player player){
		player.draw(2);
		player.increaseActions(1);
	}
}
