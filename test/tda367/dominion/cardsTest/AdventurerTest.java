package tda367.dominion.cardsTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tda367.dominion.cards.Adventurer;
import tda367.dominion.model.Pile;
import tda367.dominion.model.Player;

public class AdventurerTest {

	@Test
	public void testPlay() {
		Player p = new Player("Wrath");
		int random = (int)Math.random() * 100;
		for(int i=random;i>0;i--) {
			p.gain("Curse");
		}
		p.discardHand();
		Adventurer.play(p);
		Pile test = p.getHand();
		assertTrue(test.getSize() == 2);
		assertTrue(test.pop().equals("Copper"));
		assertTrue(test.pop().equals("Copper"));
	}

}
