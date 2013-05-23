package tda367.dominion.server.game.cards;

import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.Player;

public class Village {
	public static void play(Dominion game) {
		Player player = game.getActivePlayer();
		player.draw();
		player.increaseActions(2);
	}
}
