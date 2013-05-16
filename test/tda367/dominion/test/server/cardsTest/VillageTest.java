package tda367.dominion.test.server.cardsTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.cards.Village;

public class VillageTest {

	@Test
	public void testPlay() {
		Player p = new Player(0, "HNNNG");
		int actions = p.getActions();
		int handsize = p.getHandSize();
		Village.play(p);
		int actionsDiff = p.getActions() - actions;
		int handsizeDiff = p.getHandSize() - handsize;
		assertTrue(actionsDiff == 2);
		assertTrue(handsizeDiff == 1);
		
	}

}
