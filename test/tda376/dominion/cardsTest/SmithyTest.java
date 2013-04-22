package tda376.dominion.cardsTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tda367.dominion.cards.Smithy;
import tda367.dominion.model.Player;

public class SmithyTest {

	@Test
	public void testPlay() {
		Player p = new Player("Mr.Smith");
		int cards = p.getHandSize();
		Smithy.play(p);
		assertTrue(p.getHandSize() - cards == 3);
	}

}
