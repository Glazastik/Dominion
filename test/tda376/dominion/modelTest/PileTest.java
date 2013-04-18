package tda376.dominion.modelTest;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import tda376.dominion.model.Pile;

public class PileTest {

	@Test
	public void testPile() {
		Pile p = new Pile();
		
		assertTrue(p.getSize() == 0);
	}

	@Test
	public void testPileListOfCard() {
		LinkedList<String> l = new LinkedList<String>();
		l.add("TEST_1");
		l.add("TEST_2");
		l.add("TEST_3");
		l.add("TEST_4");
		l.add("TEST_5");
		Pile p = new Pile(l);
		
		assertTrue(l.size() == p.getSize());
	}

	@Test
	public void testAdd() {
		int addedCards = 3;
		Pile p = new Pile();
		p.add("TEST_1");
		p.add("TEST_2");
		p.add("TO_POP");
		
		//Checks that the right amount has been added and
		//that it has been added in the correct order
		assertTrue(p.getSize() == addedCards);
		assertTrue(p.pop().equals("TO_POP"));
	}
	
	@Test 
	public void testAddPile() {
		Pile p = new Pile();
		p.add("Darth Yoda");
		p.add("Nuck Chorris");
		Pile l = new Pile();
		l.add(p);
		assertTrue(l.contains("Darth Yoda")); 
		assertTrue(l.contains("Nuck Chorris"));
		assertTrue(l.getSize() == 2);
	}

	@Test
	public void testPop() {
		int addedCards1 = 3;
		LinkedList<String> l = new LinkedList<String>();
		l.add("TEST_1");
		l.add("TEST_2");
		l.add("TEST_3");
		Pile p1 = new Pile(l);
		
		int addedCards2 = 1;
		String testString = "TEST_POP";
		Pile p2 = new Pile();
		p2.add(testString);
		
		assertTrue(l.getFirst().equals(p1.pop()));
		assertTrue(p1.getSize() == (addedCards1 - 1));
		assertTrue(testString.equals(p2.pop()));
		assertTrue(p2.getSize() == (addedCards2 - 1));
	}
	
	@Test
	public void testStringPop() {
		int addedCards1 = 3;
		LinkedList<String> l = new LinkedList<String>();
		l.add("TEST_1");
		l.add("TEST_2");
		l.add("TEST_3");
		Pile p1 = new Pile(l);
		
		int addedCards2 = 4;
		Pile p2 = new Pile();
		p2.add("NOT_TO_POP");
		p2.add("NOT_TO_POP");
		p2.add("TO_POP");
		p2.add("NOT_TO_POP");
		
		assertTrue(p1.pop("TEST_2").equals("TEST_2"));
		assertTrue(p1.getSize() == (addedCards1 - 1));
		assertTrue(p2.pop("TO_POP").equals("TO_POP"));
		assertTrue(p2.getSize() == (addedCards2 - 1));
	}

	@Test
	public void testShuffle() {
		LinkedList<String> l = new LinkedList<String>();
		l.add("TEST_1");
		l.add("TEST_2");
		l.add("TEST_3");
		l.add("TEST_4");
		l.add("TEST_5");
		l.add("TEST_6");
		l.add("TEST_7");
		l.add("TEST_8");
		l.add("TEST_9");
		l.add("TEST_10");
		Pile p = new Pile(l);
		p.shuffle();
		
		assertFalse(l.equals(p.getCards()));
	}

	@Test
	public void testGetSize() {
		LinkedList<String> l = new LinkedList<String>();
		l.add("TEST_1");
		l.add("TEST_2");
		l.add("TEST_3");
		Pile p1 = new Pile(l);
		
		int nbrOfAddedCards = 4;
		Pile p2 = new Pile();
		p2.add("TEST_1");
		p2.add("TEST_2");
		p2.add("TEST_3");
		p2.add("TEST_4");
		assertTrue(p1.getSize() == l.size());
		assertTrue(nbrOfAddedCards == p2.getSize());
	}

	@Test
	public void testGetCards() {
		LinkedList<String> l = new LinkedList<String>();
		l.add("TEST_1");
		l.add("TEST_2");
		l.add("TEST_3");
		Pile p1 = new Pile(l);
		
		//Since add behaves as addFirst, the order must be reversed
		Pile p2 = new Pile();
		p2.add("TEST_3");
		p2.add("TEST_2");
		p2.add("TEST_1");
		assertTrue(l.equals(p1.getCards())); 
		assertTrue(l.equals(p2.getCards()));
	}
	
	@Test
	public void testGetTop() {
		LinkedList<String> l = new LinkedList<String>();
		l.add("TEST_1");
		l.add("TEST_2");
		l.add("TEST_3");
		Pile p1 = new Pile(l);
		
		//Since add behaves as addFirst, the order must be reversed
		Pile p2 = new Pile();
		p2.add("TEST_3");
		p2.add("TEST_2");
		p2.add("TEST_1");
		
		assertTrue(p1.getTop().equals("TEST_1"));
		assertTrue(p2.getTop().equals("TEST_1"));
	}
	
	@Test
	public void testGetTopNull() {
		
	}
	
	@Test
	public void testGetTopSeveral() {
		
	}

	@Test
	public void testContains() {
		
	}
}
