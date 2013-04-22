package tda367.dominion.cardsTest;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import tda367.dominion.cards.Witch;
import tda367.dominion.model.GainingHandler;
import tda367.dominion.model.Player;
import tda367.dominion.model.Supply;

public class WitchTest {

	@Test
	public void testPlay() {
		Player p1 = new Player("Yoda");
		Player p2 = new Player("Vader");
		Player p3 = new Player("Luke");
		Supply supply = new Supply(3);
		LinkedList<Player> players = new LinkedList<Player>();
		players.add(p1);
		players.add(p2);
		players.add(p3);
		GainingHandler gh = new GainingHandler(supply);
		Witch.play(p1, players, gh);
		boolean p1curse = p1.getDiscardPile().contains("Curse");
		boolean p2curse = p2.getDiscardPile().contains("Curse");
		boolean p3curse = p3.getDiscardPile().contains("Curse");
		assertTrue(p1curse == false && p2curse == true && p3curse == true);
	}

}
