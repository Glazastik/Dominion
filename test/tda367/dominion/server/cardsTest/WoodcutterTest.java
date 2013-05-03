package tda367.dominion.server.cardsTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tda367.dominion.server.cards.Woodcutter;
import tda367.dominion.server.model.Player;

public class WoodcutterTest {

	@Test
	public void testPlay() {
		Player p = new Player("Chuck");
		Woodcutter.play(p);
		assertTrue(p.getBuys() == 2 && p.getMoney() == 2);
	}

}
