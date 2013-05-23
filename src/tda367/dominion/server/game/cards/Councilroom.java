package tda367.dominion.server.game.cards;

import java.util.LinkedList;

import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.Player;

public class Councilroom {
	public static void play(Dominion game){
		Player player = game.getActivePlayer();
		player.draw(4);
		player.increaseBuy(1);
		
		LinkedList<Player> players = game.getInactivePlayers();
		for(Player p :players){
			p.draw();
		}
	}
}
