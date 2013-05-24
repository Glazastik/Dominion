package tda367.dominion.test.server.cardsTest;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.cards.Festival;

public class FestivalTest {

	@Test
	public void testPlay() {
		Player p = new Player(0, "Ragnar");
		
		LinkedList<Player> players = new LinkedList<Player>();
		players.add(p);
		Dominion game = new Dominion(players);
		
		int buys = p.getBuys();
		int actions = p.getActions();
		int money = p.getMoney();
		Festival.play(game);
		assertTrue(p.getBuys() - buys == 1);
		assertTrue(p.getActions() - actions == 2);
		assertTrue(p.getMoney() - money == 2);
	}

}
