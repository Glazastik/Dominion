package tda367.dominion.test.server.modelTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tda367.dominion.server.game.Player;

public class PlayerTest {

	@Test
	public void testPlayer() {
		Player p = new Player("Hajo");

		assertTrue(p.getActions() == 1);
		assertTrue(p.getBuys() == 1);
		assertTrue(p.getMoney() == 0);
		assertTrue(p.getHandSize() == 5);
		assertTrue(p.getDiscardPile().getSize() == 0);
		assertTrue(p.getDeckSize() == 5);
	}

	@Test
	public void testDraw() {
		Player p = new Player("Hajo");
		int x = p.getHandSize();
		p.draw();
		int y = p.getHandSize();
		assertTrue(y == x + 1);
	}

	@Test
	public void testDrawInt() {
		Player p = new Player("Hajo");
		int x = p.getHandSize();
		p.draw(3);
		int y = p.getHandSize();
		assertTrue(y == x + 3);
	}

	@Test
	public void testDiscardHand() {
		Player p = new Player("Hajo");
		p.draw(2);
		p.discardHand();
		assertTrue(p.getHandSize() == 0);
	}

	@Test
	public void testDiscardCard() {
		Player p = new Player("Hajo");
		p.discardCard("Copper");
		assertTrue(p.getHandSize() == 4);
	}

	@Test
	public void testGain() {
		Player p = new Player("Hajo");
		p.gain("Curse");
		p.draw(6);
		assertTrue(p.getHandSize() == 11);
	}

	@Test
	public void testAddToHand() {
		Player p = new Player("Hajo");
		p.addToHand("Curse");
		assertTrue(p.getHandSize() == 6);
	}

	@Test
	public void testRevealHand() {
		Player p = new Player("Hajo");
		p.draw(3);
		assertTrue(p.getHandSize() == 8);
	}

	@Test
	public void testDiscardFromDeck() {
		Player p = new Player("Hajo");
		p.discardDeck();
		assertTrue(p.getDeckSize() == 0);
	}

	@Test
	public void testPutOnTopOfDeck() {
		Player p = new Player("Hajo");
		p.putOnTopOfDeck("Curse");
		assertTrue(p.getDeckSize() == 6);
	}
	
	@Test
	public void testIncreaseMoney() {
		Player p = new Player("Hajo");
		p.increaseMoney(5);
		assertTrue(p.getMoney() == 5);
	}
	
	@Test
	public void testDecreaseMoney() {
		Player p = new Player("Hajo");
		p.increaseMoney(6);
		p.decreaseMoney(3);
		assertTrue(p.getMoney() == 3);
	}
	
	@Test
	public void testIncreaseActions() {
		Player p = new Player("Hajo");
		p.increaseActions(2);
		assertTrue(p.getActions() == 3);
	}
	
	@Test
	public void testIncreaseBuy() {
		Player p = new Player("Hajo");
		p.increaseBuy(666);
		assertTrue(p.getBuys() == 667);
	}
	
	@Test
	public void testDiscardPileToDeck() {
		Player p = new Player("Hajo");
		p.draw(5);
		p.discardHand();
		p.draw(3);
		assertTrue(p.getDeckSize() == 7 && p.getHandSize() == 3);
	}
	
	@Test
	public void testDecreaseBuy() {
		Player p = new Player("Hajo");
		p.decreaseBuy(1);
		assertTrue(p.getBuys() == 0);
	}
	
	@Test
	public void testDecreaseAction() {
		Player p = new Player("Hajo");
		p.decreaseActions(1);
		assertTrue(p.getActions() == 0);
	}
	
	@Test
	public void testPlay() {
		Player p = new Player("Hajo");
		p.addToHand("Moat");
		p.play("Moat");
		assertTrue(p.getHandSize() == 5 && p.getActions() == 0);
	}
	
	@Test
	public void testCleanUp() {
		Player p = new Player("Hajo");
		p.addToHand("Moat");
		p.play("Moat");
		p.cleanUp();
		assertTrue(p.getDiscardPile().contains("Moat"));
	}
	
	@Test
	public void testPutRevealedCardsInDiscard() {
		Player p = new Player("Hajo");
		p.putOnTopOfDeck("Platinum");
		p.putOnTopOfDeck("Curse");
		p.setAsideTopOfDeck();
		p.setAsideTopOfDeck();
		p.putRevealedCardsInDiscard();
		assertTrue(p.getDiscardPile().contains("Platinum") && p.getDiscardPile().contains("Curse") && p.getDiscardPile().getSize() == 2);
	}
	
	@Test
	public void testSetAsideTopOfDeck() {
		Player p = new Player("Hajo");
		p.putOnTopOfDeck("Chuck Norris");
		p.setAsideTopOfDeck();
		assertTrue(p.getRevealedCards().contains("Chuck Norris"));
	}
	
	@Test
	public void testEquals() {
		Player p1 = new Player("Chuck Testa");
		Player p2 = new Player("Chuck Norris");
		Player p3 = new Player("Chuck Norris");
		assertFalse(p2.equals(p3));
		assertFalse(p2.equals(p1));
		assertTrue(p1.equals(p1));
	}
	
	@Test
	public void testTrashCard() {
		Player p = new Player("Hajo");
		String trash = p.trashCard("Copper");
		assertTrue(trash == "Copper");
	}
	
	@Test
	public void testDrawWhenAllEmpty() {
		Player p = new Player("Hajo");
		p.draw(5);
		p.discardHand();
		for(int i = 0; i < 10; i++){
			p.getDiscardPile().pop();
		}
		p.draw(5);
		assertTrue(p.getHandSize() == 0);
	}
	
	@Test
	public void testRevealTopOfDeck(){
		Player p = new Player("Hajo");
		p.draw(5);
		System.out.println(p.revealTopOfDeck());
		
		p.gain("Curse");
		p.gain("Curse");
		
		System.out.println(p.revealTopOfDeck());
		assertTrue(p.revealTopOfDeck().equals("Curse"));
		
	}
}
