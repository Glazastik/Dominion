package tda367.dominion.test.server.cardsTest;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.cards.Chancellor;

public class ChancellorTest {
	
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
		
		int money = p.getMoney();
		
		Chancellor c = new Chancellor(game);
		c.play();
		
		assertTrue(p.getMoney() - money == 2);
	}
	
	@Test 
	public void testDiscardDeck() {
		Dominion game = this.initGame();
		Player p = game.getActivePlayer();
		
		p.discardDeck();
		
		assertTrue(p.getDeckSize() == 0);
	}

}
