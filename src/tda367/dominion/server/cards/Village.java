package tda367.dominion.server.cards;

import tda367.dominion.server.model.Player;

public class Village implements ICard {
	public static void play(Player player){
		player.draw();
		player.increaseActions(2);	
	}
}
