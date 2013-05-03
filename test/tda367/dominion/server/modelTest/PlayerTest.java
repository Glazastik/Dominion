package tda367.dominion.modelTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import tda367.dominion.model.Player;

public class PlayerTest {

	@Test
	public void testPlayer() {
		Player p = new Player("Player");

		assertTrue(p.getActions() == 1);
		assertTrue(p.getBuys() == 1);
		assertTrue(p.getMoney() == 0);
		assertTrue(p.getHandSize() == 5);
		assertTrue(p.getDiscardPile().getSize() == 0);
		assertTrue(p.getDeckSize() == 5);
	}

	@Test
	public void testDraw() {
		Player p = new Player("Test");
		int x = p.getHandSize();
		p.draw();
		int y = p.getHandSize();
		assertTrue(y == x + 1);
	}

	@Test
	public void testDrawInt() {
		Player p = new Player("Test");
		int x = p.getHandSize();
		p.draw(3);
		int y = p.getHandSize();
		assertTrue(y == x + 3);
	}

	@Test
	public void testDiscardHand() {
		Player p = new Player("Test");
		p.draw(2);
		p.discardHand();
		assertTrue(p.getHandSize() == 0);
	}

	@Test
	public void testDiscardCard() {
		Player p = new Player("Test");
		p.discardCard("Copper");
		assertTrue(p.getHandSize() == 4);
	}

	@Test
	public void testGain() {
		Player p = new Player("Test");
		p.gain("Curse");
		p.draw(6);
		assertTrue(p.getHandSize() == 11);
	}

	@Test
	public void testAddToHand() {
		Player p = new Player("Test");
		p.addToHand("Curse");
		assertTrue(p.getHandSize() == 6);
	}

	@Test
	public void testRevealHand() {
		Player p = new Player("Test");
		p.draw(3);
		assertTrue(p.getHandSize() == 8);
	}

	@Test
	public void testDiscardFromDeck() {
		Player p = new Player("Test");
		p.discardDeck();
		assertTrue(p.getDeckSize() == 0);
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
		assertTrue(p.getDeckSize() == 7 && p.getHandSize() == 3);
	}
	
	@Test
	public void testDecreaseBuy() {
		Player p = new Player("Test");
		p.decreaseBuy(1);
		assertTrue(p.getBuys() == 0);
	}
	
	@Test
	public void testDecreaseAction() {
		Player p = new Player("Test");
		p.decreaseActions(1);
		assertTrue(p.getActions() == 0);
	}
	
	@Test
	public void testPlay() {
		Player p = new Player("Test");
		p.addToHand("Moat");
		p.play("Moat");
		assertTrue(p.getHandSize() == 5 && p.getActions() == 0);
	}
	
	@Test
	public void testCleanUp() {
		Player p = new Player("Test");
		p.addToHand("Moat");
		p.play("Moat");
		p.cleanUp();
		assertTrue(p.getDiscardPile().contains("Moat"));
	}
	
	@Test
	public void testPutRevealedCardsInDiscard() {
		Player p = new Player("Test");
		p.putOnTopOfDeck("Platinum");
		p.putOnTopOfDeck("Curse");
		p.setAsideTopOfDeck();
		p.setAsideTopOfDeck();
		p.putRevealedCardsInDiscard();
		assertTrue(p.getDiscardPile().contains("Platinum") && p.getDiscardPile().contains("Curse") && p.getDiscardPile().getSize() == 2);
	}
	
	@Test
	public void testSetAsideTopOfDeck() {
		Player p = new Player("Test");
		p.putOnTopOfDeck("Chuck Norris");
		p.setAsideTopOfDeck();
		assertTrue(p.getRevealedCards().contains("Chuck Norris"));
	}
	
	@Test
	public void testEquals() {
		Player p1 = new Player("Chuck Testa");
		Player p2 = new Player("Chuck Norris");
		Player p3 = new Player("Chuck Norris");
		assertTrue(p2.equals(p3));
		assertFalse(p2.equals(p1));
	}
	
	@Test
	public void testTrashCard() {
		Player p = new Player("Trash Meister");
		String trash = p.trashCard("Estate");
		assertTrue(trash == "Estate");
	}
	
	@Test
	public void testDrawWhenAllEmpty() {
		Player p = new Player("Player");
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
		Player p = new Player("Player");
		p.draw(5);
		System.out.println(p.revealTopOfDeck());
//		List<String> lista = p.revealTopOfDeck();
//		for(String s : lista){
//			System.out.println(s);
//		}
	}
}
