package tda367.dominion.test.server.cardsTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.cards.Chancellor;

public class ChancellorTest {

	@Test
	public void testPlay() {
		Player p = new Player(0, "Man");
		int money = p.getMoney();
		Chancellor.play(p);
		assertTrue(p.getMoney() - money == 2);		
	}
	
	@Test 
	public void testDiscardDeck() {
		Player p = new Player(1, "Man #2");
//		Chancellor.discardDeck(p);
		assertTrue(p.getDeckSize() == 0);
	}

}
