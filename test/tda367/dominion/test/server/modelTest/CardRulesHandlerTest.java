package tda367.dominion.test.server.modelTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

import tda367.dominion.server.game.CardRulesHandler;
import tda367.dominion.server.game.Pile;
import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.Supply;

public class CardRulesHandlerTest {

	@Test
	public void playCardTest() {
		Player player1 = new Player(0, "Wasa");
		Player player2 = new Player(1, "Kristian");
		LinkedList<Player> players = new LinkedList<Player>();
		players.add(player1);
		players.add(player2);
		Supply supply = new Supply(players.size());
		CardRulesHandler rulesHandler = new CardRulesHandler(players, supply);
		player1.addToHand("Village");
		rulesHandler.playCard(player1, "Village");
		assertTrue(player1.getActions() == 2);
		assertTrue(player1.getHandSize() == 6);
		player1.addToHand("Witch");
		rulesHandler.playCard(player1, "Witch");
		assertTrue(player1.getActions() == 1);
		assertTrue(player1.getHandSize() == 8);
		Pile discard2 = player2.getDiscardPile();
		assertTrue(discard2.contains("Curse"));
		Pile discard1 = player1.getDiscardPile();
		assertFalse(discard1.contains("Curse"));
	}

}
