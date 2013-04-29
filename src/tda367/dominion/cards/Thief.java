package tda367.dominion.cards;

import java.util.LinkedList;

import tda367.dominion.model.Player;

public class Thief {
	public static void play(Player p, LinkedList<Player> players){
		for(Player player : players){
			if(player!=p){
				LinkedList<String> tempList = new LinkedList<String>();
				tempList.addAll(player.revealTopOfDeck(2));	
			}
		}
	}
}
