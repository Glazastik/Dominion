package tda367.dominion.cards;

import tda367.dominion.model.Player;

public class Moat implements ICard {
	public static void play(Player player){
		player.draw(2);
	}
}
