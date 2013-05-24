package tda367.dominion.test.server.cardsTest;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.cards.Chancellor;

public class ChancellorTest {

	@Test
	public void testPlay() {
		Player p = new Player(0, "Man");
		LinkedList<Player> players = new LinkedList<Player>();
		players.add(p);
		Dominion game = new Dominion(players);
		
		int money = p.getMoney();
		Chancellor c = new Chancellor(game);
		c.play();
		assertTrue(p.getMoney() - money == 2);		
	}
	
	@Test 
	public void testDiscardDeck() {
		Player p = new Player(1, "Man #2");
//		Chancellor.discardDeck(p);
		assertTrue(p.getDeckSize() == 0);
	}

}
