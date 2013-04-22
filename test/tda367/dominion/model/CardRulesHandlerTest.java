package tda367.dominion.model;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import tda367.dominion.model.CardRulesHandler;
import tda367.dominion.model.GainingHandler;
import tda367.dominion.model.Player;
import tda367.dominion.model.Supply;

public class CardRulesHandlerTest {

	@Test
	public void playCardTest() {
		Player player1 = new Player("Wasa");
		Player player2 = new Player("Kristian");
		LinkedList<Player> players = new LinkedList<Player>();
		players.add(player1);
		players.add(player2);
		Supply supply = new Supply(players.size());
		GainingHandler gainingHandler = new GainingHandler(supply);
		CardRulesHandler rulesHandler = new CardRulesHandler(players, gainingHandler);
		rulesHandler.playCard(player1, "Village");
		//assertTrue()
		
	}

}
