package tda367.dominion.server.game.cards;

import tda367.dominion.server.game.Player;

public class Moat implements ICard {
	public static void play(Player player){
		player.draw(2);
	}
}
