package tda367.dominion.server.cards;

import tda367.dominion.server.model.Player;

public class Moat implements ICard {
	public static void play(Player player){
		player.draw(2);
	}
}
