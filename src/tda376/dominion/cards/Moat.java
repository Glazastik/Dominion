package tda376.dominion.cards;

import tda376.dominion.model.Player;

public class Moat implements ICard {
	public static void play(Player player){
		player.draw(2);
	}
}
