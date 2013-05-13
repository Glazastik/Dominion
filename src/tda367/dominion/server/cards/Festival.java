package tda367.dominion.server.cards;

import tda367.dominion.server.game.Player;

public class Festival implements ICard {
	public static void play(Player player){
		player.increaseBuy(1);
		player.increaseMoney(2);
		player.increaseActions(2);
	}
}
