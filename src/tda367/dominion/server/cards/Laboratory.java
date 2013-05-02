package tda367.dominion.server.cards;

import tda367.dominion.server.model.Player;

public class Laboratory implements ICard {
	public static void play(Player player){
		player.draw(2);
		player.increaseActions(1);
	}
}
