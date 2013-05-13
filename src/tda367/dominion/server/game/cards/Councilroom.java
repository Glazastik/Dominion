package tda367.dominion.server.game.cards;

import java.util.LinkedList;

import tda367.dominion.server.game.Player;

public class Councilroom implements ICard {
	public static void play(Player player, LinkedList<Player> players){
		player.draw(4);
		player.increaseBuy(1);
		for(Player p :players){
			if(p!=player){
				p.draw();
			}
		}
	}
}
