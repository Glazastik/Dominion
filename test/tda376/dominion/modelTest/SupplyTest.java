package tda376.dominion.modelTest;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

import tda376.dominion.model.Supply;

public class SupplyTest {

	@Test
	public void getActiveCardsTest() {
		int players = 2;
		Supply supply = new Supply(players);
		HashMap<String,Integer> activeCards = supply.getActiveCards();
		assertTrue(activeCards.size() == 17);
		Set<String> iteratorSet = activeCards.keySet();
		Iterator i = iteratorSet.iterator();
		assertTrue(activeCards.get("Curse") == (players-1)*10);
	}

}
