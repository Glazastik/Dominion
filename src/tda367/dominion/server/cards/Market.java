package tda367.dominion.server.cards;

import tda367.dominion.server.model.Player;

public class Market {
	public static void play(Player player){
		player.increaseMoney(1);
		player.draw();
		player.increaseActions(1);
		player.increaseBuy(1);
	}
}
