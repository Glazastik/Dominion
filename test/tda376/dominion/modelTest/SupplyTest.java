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
	@Test
	public void immutableTest(){
		Supply supply = new Supply(2);
		HashMap<String,Integer> activeCards = supply.getActiveCards();
		activeCards.put("Province", 0);
		assertFalse(supply.gameIsOver());
		
	}
	@Test
	public void takeTest(){
		int players = 2;
		Supply supply = new Supply(players);
		
	}
	@Test
	public void gameIsOverTest() {
		int players = 2;
		Supply supply = new Supply(players);
		assertFalse(supply.gameIsOver());
		int loopTal = 0;
		while(loopTal<8){
			supply.take("Province");
			loopTal++;
		}
		assertTrue(supply.gameIsOver());
		
		Supply supply2 = new Supply(players);
		assertFalse(supply2.gameIsOver());
		HashMap<String,Integer> activeCards = supply2.getActiveCards();
		Set<String> iteratorSet = activeCards.keySet();
		Iterator i = iteratorSet.iterator();
		String tempString;
		int loopTalet = 0;
		while(loopTalet<3){
			loopTal = 0;
			tempString = (String) i.next();
			while(loopTal<12){
				supply2.take(tempString);
				loopTal++;
			}
			loopTalet++;
		}
		assertTrue(supply2.gameIsOver());
	}

}
