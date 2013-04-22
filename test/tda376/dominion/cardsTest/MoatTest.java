package tda376.dominion.cardsTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tda376.dominion.cards.Moat;
import tda376.dominion.model.Player;

public class MoatTest {

	@Test
	public void testPlay() {
		Player p = new Player("Mr.Boat");
		int cards = p.getHandSize();
		Moat.play(p);
		assertTrue(p.getHandSize() - cards == 2);
	}

}
