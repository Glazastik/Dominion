package tda376.dominion.cardsTest;

import static org.junit.Assert.*;
import tda376.dominion.cards.Woodcutter;
import tda376.dominion.model.*;
import org.junit.Test;

public class WoodcutterTest {

	@Test
	public void testPlay() {
		Player p = new Player("Chuck");
		Woodcutter.play(p);
		assertTrue(p.getBuys() == 2 && p.getMoney() == 2);
	}

}
