package tda367.dominion.test.server.cardsTest;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.cards.Woodcutter;

public class WoodcutterTest {

	@Test
	public void testPlay() {
		Player p = new Player(0, "Chuck");
		LinkedList<Player> players = new LinkedList<Player>();
		players.add(p);
		Dominion game = new Dominion(players);
		Woodcutter.play(game);
		assertTrue(p.getBuys() == 2 && p.getMoney() == 2);
	}

}
