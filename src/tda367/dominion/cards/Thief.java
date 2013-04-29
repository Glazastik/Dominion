package tda367.dominion.cards;

import java.util.LinkedList;

import tda367.dominion.model.CardInfoHandler;
import tda367.dominion.model.Player;

public class Thief {
	
	public static void play(Player p, LinkedList<Player> players){
		CardInfoHandler cif = CardInfoHandler.getInstance();
		boolean hasTreasure  = false;
		for(Player player : players){
			hasTreasure = false;
			if(player!=p){
				LinkedList<String> tempList = new LinkedList<String>();
				tempList.addAll(player.revealTopOfDeck(2));
				for(String s: tempList){
					if(cif.getCardType(s).equals("Treasure")){
						hasTreasure = true;
					}
				}
			}
		}
	}
}
