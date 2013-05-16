package tda367.dominion.test.server.cardsTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.cards.Laboratory;

public class LaboratoryTest {

	@Test
	public void testPlay() {
		Player p = new Player(0, "King Rex");
		int handsize = p.getHandSize();
		int actions = p.getActions();
		Laboratory.play(p);
		assertTrue(p.getHandSize() - handsize == 2);
		assertTrue(p.getActions() - actions == 1);
	}

}
