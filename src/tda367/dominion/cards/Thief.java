package tda367.dominion.cards;

import java.util.LinkedList;

import tda367.dominion.model.CardInfoHandler;
import tda367.dominion.model.Player;

public class Thief {
	
	public static void play(Player p, LinkedList<Player> players){
		CardInfoHandler cif = CardInfoHandler.getInstance();
		boolean hasTreasure  = false;
		LinkedList<String> trashedCards = new LinkedList<String>();
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
				if(hasTreasure){
					//p.sendMultipleCardMessage(tempList);
					//boolean done = false;
					//while(!done){
					//	Message message = p.getNextMessage();
					//	if(message instanceOf LocatedCardMessage){
					//		LocatedCardMessage tempMessage = (LocatedCardMessage) message;
					//		if(tempMessage.getLocation().equals(Revealed) && cif.getCardType(tempMessage.getCardName()).equals("Treasure")){
					//			player.trashFromDeck(tempMessage.getCardName());
					//			done = true;
					//		}
					//	}
					//}
				}
			}
		}
	}
}
