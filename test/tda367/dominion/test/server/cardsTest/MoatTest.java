package tda367.dominion.test.server.cardsTest;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.cards.Moat;

public class MoatTest {

	@Test
	public void testPlay() {
		Player p = new Player(0, "Mr.Boat");
		LinkedList<Player> players = new LinkedList<Player>();
		players.add(p);
		Dominion game = new Dominion(players);
		int cards = p.getHandSize();
		Moat.play(game);
		assertTrue(p.getHandSize() - cards == 2);
	}

}
