package tda367.dominion.test.server.cardsTest;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.cards.Councilroom;
import tda367.dominion.server.game.cards.Throneroom;

public class CouncilroomTest {

	private Dominion initGame() {
		Player p1 = new Player("Markolio");
		Player p2 = new Player("Knugen");
		Player p3 = new Player("Bacchus");
		LinkedList<Player> players = new LinkedList<Player>();
		players.add(p1);
		players.add(p2);
		players.add(p3);
		
		Dominion game = new Dominion(players);		
		Councilroom.play(game);
		return game;
	}
	
	@Test
	public void testSelfGain() {
		Dominion game = this.initGame();
		
		assertTrue(game.getActivePlayer().getHandSize() == 9);
	}
	
	@Test
	public void testOthersGain() {
		Dominion game = this.initGame();
		LinkedList<Player> l = game.getInactivePlayers();
		for(Player p : l) {
			assertTrue(p.getHandSize() == 6);
		}
	}
	
	@Test
	public void testSelfBuys() {
		Dominion game = this.initGame();
		assertTrue(game.getActivePlayer().getBuys() == 2);
	}

}
