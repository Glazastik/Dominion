package tda376.dominion.cardsTest;

import static org.junit.Assert.*;

import org.junit.Test;

import tda367.dominion.cards.Village;
import tda367.dominion.model.*;

public class VillageTest {

	@Test
	public void testPlay() {
		Player p = new Player("HNNNG");
		int actions = p.getActions();
		int handsize = p.getHandSize();
		Village.play(p);
		int actionsDiff = p.getActions() - actions;
		int handsizeDiff = p.getHandSize() - handsize;
		assertTrue(actionsDiff == 2);
		assertTrue(handsizeDiff == 1);
		
	}

}
