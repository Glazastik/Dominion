package tda367.dominion.test.server.cardsTest;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.cards.Witch;

public class WitchTest {
	
	private Dominion initGame() {
		Player p1 = new Player("Markolio");
		Player p2 = new Player("Knugen");
		Player p3 = new Player("Bacchus");
		LinkedList<Player> players = new LinkedList<Player>();
		players.add(p1);
		players.add(p2);
		players.add(p3);
		
		Dominion game = new Dominion(players);		
		return game;
	}
	
	@Test
	public void testPlay() {
		Dominion game = this.initGame();
		Player p = game.getActivePlayer();
		
		int hand = p.getHandSize();
		
		Witch.play(game);
		
		assertTrue(p.getHandSize() - hand == 2);
	}
	
	@Test
	public void testCurse() {
		Dominion game = this.initGame();
		
		Witch.play(game);
		
		LinkedList<Player> l = game.getInactivePlayers();
		
		for(Player p : l) {
			assertTrue(p.getDiscardPile().contains("Curse"));
		}
	}

}
