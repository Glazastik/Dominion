package tda376.dominion.cards;

import tda376.dominion.model.Player;

public class Market {
	public static void play(Player player){
		player.increaseMoney(1);
		player.draw();
		player.increaseActions(1);
		player.increaseBuy(1);
	}
}