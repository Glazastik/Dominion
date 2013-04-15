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
		Player p = new Player("Test");
		p.gain("Curse");
		p.draw(6);
		assertTrue(p.revealHand().size() == 11);
	}

	@Test
	public void testAddToHand() {
		Player p = new Player("Test");
		p.addToHand("Curse");
		assertTrue(p.revealHand().size() == 6);
	}

	@Test
	public void testRevealHand() {
		Player p = new Player("Test");
		p.draw(3);
		assertTrue(p.revealHand().size() == 8);
	}

	@Test
	public void testDiscardFromDeck() {
		Player p = new Player("Test");
		//Not sure if this is how discardFromDeck should work
		//p.discardFromDeck(1);
		p.discardFromDeck("Copper");
		assertTrue(p.getDeckSize() == 4);
	}

	@Test
	public void testPutOnTopOfDeck() {
		Player p = new Player("Test");
		p.putOnTopOfDeck("Curse");
		assertTrue(p.getDeckSize() == 6);
	}
	
	@Test
	public void testIncreaseMoney() {
		Player p = new Player("Test");
		p.increaseMoney(5);
		assertTrue(p.getMoney() == 5);
	}
	
	@Test
	public void testDecreaseMoney() {
		Player p = new Player("Test");
		p.increaseMoney(6);
		p.decreaseMoney(3);
		assertTrue(p.getMoney() == 3);
	}
	
	@Test
	public void testIncreaseActions() {
		Player p = new Player("Test");
		p.increaseActions(2);
		assertTrue(p.getActions() == 3);
	}
	
	@Test
	public void testIncreaseBuy() {
		Player p = new Player("Test");
		p.increaseBuy(666);
		assertTrue(p.getBuys() == 667);
	}
	
	@Test
	public void testDiscardPileToDeck() {
		Player p = new Player("Test");
		p.draw(5);
		p.discardHand();
		p.draw(3);
		assertTrue(p.getDeckSize() == 7 && p.revealHand().size() == 3);
	}
	
	@Test
	public void testDecreaseBuy() {
		Player p = new Player("Test");
		p.decreaseBuy(1);
		assertTrue(p.getBuys() == 0);
	}
	

}
