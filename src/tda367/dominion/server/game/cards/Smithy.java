package tda367.dominion.server.game.cards;

import tda367.dominion.server.game.Dominion;

public class Smithy {
	public static void play(Dominion game) {
		game.getActivePlayer().draw(3);
	}
}
