package tda367.dominion.test.server.cardsTest;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.Supply;
import tda367.dominion.server.game.cards.Witch;

public class WitchTest {

	@Test
	public void testPlay() {
		Player p1 = new Player(0, "Yoda");
		Player p2 = new Player(1, "Vader");
		Player p3 = new Player(2, "Luke");
		Supply supply = new Supply(3);
		LinkedList<Player> players = new LinkedList<Player>();
		players.add(p1);
		players.add(p2);
		players.add(p3);
		Witch.play(p1, players, supply);
		boolean p1curse = p1.getDiscardPile().contains("Curse");
		boolean p2curse = p2.getDiscardPile().contains("Curse");
		boolean p3curse = p3.getDiscardPile().contains("Curse");
		assertTrue(p1curse == false && p2curse == true && p3curse == true);
	}

}
