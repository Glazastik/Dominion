package tda376.dominion.cardsTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tda367.dominion.cards.Chancellor;
import tda367.dominion.model.Player;

public class ChancellorTest {

	@Test
	public void testPlay() {
		Player p = new Player("Heart attack man");
		int money = p.getMoney();
		Chancellor.play(p);
		assertTrue(p.getMoney() - money == 2);		
	}
	
	@Test 
	public void testDiscardDeck() {
		Player p = new Player("Heart attack man #2");
		Chancellor.discardDeck(p);
		assertTrue(p.getDeckSize() == 0);
	}

}
