package tda376.dominion.modelTest;

import static org.junit.Assert.*;

import org.junit.Test;
import tda376.dominion.model.Player;

public class PlayerTest {

	@Test
	public void testPlayer() {
		fail("Not yet implemented");
	}

	@Test
	public void testDraw() {
		Player p = new Player("Test");
		int x = p.revealHand().size();
		p.draw();
		int y = p.revealHand().size();
		assertTrue(y == x + 1);
	}

	@Test
	public void testDrawInt() {
		Player p = new Player("Test");
		int x = p.revealHand().size();
		p.draw(3);
		int y = p.revealHand().size();
		assertTrue(y == x + 3);
	}

	@Test
	public void testDiscardHand() {
		Player p = new Player("Test");
		p.draw(2);
		p.discardHand();
		assertTrue(p.revealHand().size() == 0);
	}

	@Test
	public void testDiscardCard() {
		Player p = new Player("Test");
		p.discardCard("Copper");
		assertTrue(p.revealHand().size() == 4);
	}

	@Test
	public void testGain() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddToHand() {
		fail("Not yet implemented");
	}

	@Test
	public void testRevealHand() {
		fail("Not yet implemented");
	}

	@Test
	public void testRevealTopOfHand() {
		fail("Not yet implemented");
	}

	@Test
	public void testDiscardFromDeck() {
		fail("Not yet implemented");
	}

	@Test
	public void testPutOnTopOfDeck() {
		fail("Not yet implemented");
	}

}
