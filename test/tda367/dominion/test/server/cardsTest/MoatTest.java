package tda367.dominion.test.server.cardsTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.cards.Moat;

public class MoatTest {

	@Test
	public void testPlay() {
		Player p = new Player(0, "Mr.Boat");
		int cards = p.getHandSize();
		Moat.play(p);
		assertTrue(p.getHandSize() - cards == 2);
	}

}
