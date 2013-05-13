package tda367.dominion.test.server.cardsTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.cards.Smithy;

public class SmithyTest {

	@Test
	public void testPlay() {
		Player p = new Player("Mr.Smith");
		int cards = p.getHandSize();
		Smithy.play(p);
		assertTrue(p.getHandSize() - cards == 3);
	}

}
