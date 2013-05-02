package tda367.dominion.server.cards;

import java.util.LinkedList;

import tda367.dominion.server.model.GainingHandler;
import tda367.dominion.server.model.Player;
import tda367.dominion.server.model.Supply;

public class Witch implements ICard {
	public static void play(Player player, LinkedList<Player> players, Supply supply){
		GainingHandler gH = new GainingHandler(supply);
		player.draw(2);
		for(Player p :players){
			if(p!=player){
				gH.playerGainCard(p, "Curse");
			}
		}
	}
}
