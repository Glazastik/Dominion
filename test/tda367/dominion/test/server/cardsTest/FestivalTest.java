package tda367.dominion.test.server.cardsTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.cards.Festival;

public class FestivalTest {

	@Test
	public void testPlay() {
		Player p = new Player(0, "Ragnar");
		int buys = p.getBuys();
		int actions = p.getActions();
		int money = p.getMoney();
		Festival.play(p);
		assertTrue(p.getBuys() - buys == 1);
		assertTrue(p.getActions() - actions == 2);
		assertTrue(p.getMoney() - money == 2);
	}

}
