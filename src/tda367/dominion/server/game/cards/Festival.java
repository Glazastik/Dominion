package tda367.dominion.server.game.cards;

import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.Player;

public class Festival {
	public static void play(Dominion game) {
		Player player = game.getActivePlayer();

		player.increaseBuy(1);
		player.increaseMoney(2);
		player.increaseActions(2);
	}
}
