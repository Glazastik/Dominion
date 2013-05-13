package tda367.dominion.server.game.cards;

import tda367.dominion.server.game.Player;

public class Smithy implements ICard {
	public static void play(Player player) {
		player.draw(3);
	}
}
