package tda367.dominion.test.server.cardsTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tda367.dominion.server.cards.Moat;
import tda367.dominion.server.model.Player;

public class MoatTest {

	@Test
	public void testPlay() {
		Player p = new Player("Mr.Boat");
		int cards = p.getHandSize();
		Moat.play(p);
		assertTrue(p.getHandSize() - cards == 2);
	}

}
