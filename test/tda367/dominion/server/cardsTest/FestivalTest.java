package tda367.dominion.server.cardsTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tda367.dominion.server.cards.Festival;
import tda367.dominion.server.model.Player;

public class FestivalTest {

	@Test
	public void testPlay() {
		Player p = new Player("Ragnar");
		int buys = p.getBuys();
		int actions = p.getActions();
		int money = p.getMoney();
		Festival.play(p);
		assertTrue(p.getBuys() - buys == 1);
		assertTrue(p.getActions() - actions == 2);
		assertTrue(p.getMoney() - money == 2);
	}

}
