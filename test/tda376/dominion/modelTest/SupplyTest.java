package tda376.dominion.modelTest;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import tda376.dominion.model.Supply;

public class SupplyTest {

	@Test
	public void getActiveCardsTest() {
		Supply supply = new Supply(2);
		HashMap<String,Integer> activeCards = supply.getActiveCards();
		assertTrue(activeCards.size() == 17);
		
	}

}
