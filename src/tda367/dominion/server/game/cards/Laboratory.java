package tda367.dominion.server.game.cards;

import tda367.dominion.server.game.Player;

public class Laboratory implements ICard {
	public static void play(Player player){
		player.draw(2);
		player.increaseActions(1);
	}
}
