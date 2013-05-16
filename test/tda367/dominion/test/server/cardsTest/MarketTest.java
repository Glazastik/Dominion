package tda367.dominion.test.server.cardsTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.cards.Market;

public class MarketTest {

	@Test
	public void testPlay() {
		Player p = new Player(0, "Goko");
		int buys = p.getBuys();
		int actions = p.getActions();
		int money = p.getMoney();
		int cards = p.getHandSize();
		Market.play(p);
		assertTrue(p.getBuys() - buys == 1);
		assertTrue(p.getActions() - actions == 1);
		assertTrue(p.getMoney() - money == 1);
		assertTrue(p.getHandSize() - cards == 1);
	}

}
