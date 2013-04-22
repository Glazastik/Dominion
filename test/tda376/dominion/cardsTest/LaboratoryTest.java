package tda376.dominion.cardsTest;

import static org.junit.Assert.*;

import org.junit.Test;

import tda376.dominion.cards.Laboratory;
import tda376.dominion.model.Player;

public class LaboratoryTest {

	@Test
	public void testPlay() {
		Player p = new Player("King Rex");
		int handsize = p.getHandSize();
		int actions = p.getActions();
		Laboratory.play(p);
		assertTrue(p.getHandSize() - handsize == 2);
		assertTrue(p.getActions() - actions == 1);
	}

}
