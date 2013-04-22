package tda367.dominion.cards;

import java.util.LinkedList;

import tda367.dominion.model.GainingHandler;
import tda367.dominion.model.Player;

public class Witch implements ICard {
	public static void play(Player player, LinkedList<Player> players, GainingHandler gainingHandler){
		player.draw(2);
		for(Player p :players){
			if(p!=player){
				gainingHandler.playerGainCard(p, "Curse");
			}
		}
	}
}
