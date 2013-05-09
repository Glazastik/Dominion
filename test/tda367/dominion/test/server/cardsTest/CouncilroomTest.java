package tda367.dominion.test.server.cardsTest;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

import tda367.dominion.server.cards.Councilroom;
import tda367.dominion.server.model.Player;

public class CouncilroomTest {

	@Test
	public void testPlay() {
		Player p1 = new Player("Markolio");
		Player p2 = new Player("Knugen");
		Player p3 = new Player("Bacchus");
		LinkedList<Player> players = new LinkedList<Player>();
		players.add(p1);
		players.add(p2);
		players.add(p3);
		int p1Cards = p1.getHandSize();
		int p1Buys = p1.getBuys();
		int p2Cards = p2.getHandSize();
		int p3Cards = p3.getHandSize();
		Councilroom.play(p1, players);	
		assertTrue(p1.getBuys() - p1Buys == 1);
		assertTrue(p1.getHandSize() - p1Cards == 4);
		assertTrue(p2.getHandSize() - p2Cards == 1);
		assertTrue(p3.getHandSize() - p3Cards == 1);
	}

}
