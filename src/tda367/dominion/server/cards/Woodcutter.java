package tda367.dominion.cards;

import tda367.dominion.model.Player;

public class Woodcutter implements ICard {
	public static void play(Player player){
		player.increaseBuy(1);
		player.increaseMoney(2);
	}
}
