package tda367.dominion.cardsTest;

import static org.junit.Assert.*;

import org.junit.Test;

import tda367.dominion.cards.Laboratory;
import tda367.dominion.model.Player;

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
