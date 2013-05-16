package tda367.dominion.test.server.cardsTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tda367.dominion.server.game.Pile;
import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.cards.Adventurer;

public class AdventurerTest {

	@Test
	public void testPlay() {
		Player p = new Player(0, "Wrath");
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
