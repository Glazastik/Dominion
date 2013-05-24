package tda367.dominion.test.server.cardsTest;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.cards.Laboratory;

public class LaboratoryTest {

	@Test
	public void testPlay() {
		Player p = new Player(0, "King Rex");
		
		LinkedList<Player> players = new LinkedList<Player>();
		players.add(p);
		Dominion game = new Dominion(players);
		
		int handsize = p.getHandSize();
		int actions = p.getActions();
		Laboratory.play(game);
		assertTrue(p.getHandSize() - handsize == 2);
		assertTrue(p.getActions() - actions == 1);
	}

}
