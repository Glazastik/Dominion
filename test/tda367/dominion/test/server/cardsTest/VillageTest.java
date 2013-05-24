package tda367.dominion.test.server.cardsTest;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.cards.Village;

public class VillageTest {

	@Test
	public void testPlay() {
		Player p = new Player(0, "HNNNG");
		LinkedList<Player> players = new LinkedList<Player>();
		players.add(p);
		Dominion game = new Dominion(players);
		int actions = p.getActions();
		int handsize = p.getHandSize();
		Village.play(game);
		int actionsDiff = p.getActions() - actions;
		int handsizeDiff = p.getHandSize() - handsize;
		assertTrue(actionsDiff == 2);
		assertTrue(handsizeDiff == 1);
		
	}

}
