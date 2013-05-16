package tda367.dominion.test.server.cardsTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.cards.Woodcutter;

public class WoodcutterTest {

	@Test
	public void testPlay() {
		Player p = new Player(0, "Chuck");
		Woodcutter.play(p);
		assertTrue(p.getBuys() == 2 && p.getMoney() == 2);
	}

}
