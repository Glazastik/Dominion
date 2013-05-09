package tda367.dominion.server.cardsTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tda367.dominion.server.cards.Village;
import tda367.dominion.server.model.Player;

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
