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
		fail("Not yet implemented");
	}

	@Test
	public void testDiscardCard() {
		fail("Not yet implemented");
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
