package tda367.dominion.server.game.cards;

import tda367.dominion.server.game.Dominion;

public class Moat {
	public static void play(Dominion game){
		game.getActivePlayer().draw(2);
	}
}
