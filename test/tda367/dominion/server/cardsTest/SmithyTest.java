package tda367.dominion.server.cardsTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tda367.dominion.server.cards.Smithy;
import tda367.dominion.server.model.Player;

public class SmithyTest {

	@Test
	public void testPlay() {
		Player p = new Player("Mr.Smith");
		int cards = p.getHandSize();
		Smithy.play(p);
		assertTrue(p.getHandSize() - cards == 3);
	}

}
