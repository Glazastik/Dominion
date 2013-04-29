package tda367.dominion.cards;

import java.util.LinkedList;

import tda367.dominion.model.CardInfoHandler;
import tda367.dominion.model.Pile;
import tda367.dominion.model.Player;

public class Thief {
	
	public static void play(Player p, LinkedList<Player> players){
		CardInfoHandler cif = CardInfoHandler.getInstance();
		boolean hasTreasure  = false;
		Pile trashedCards = new Pile();
		for(Player player : players){
			hasTreasure = false;
			if(player!=p){
				Pile tempList = new Pile();
				for(String s : player.revealTopOfDeck(2)){
					tempList.add(s);
				}
//				for(String s: tempList){
//					if(cif.getCardType(s).equals("Treasure")){
//						hasTreasure = true;
//					}
//				}
				if(hasTreasure){
					//p.sendMultipleCardMessage(tempList);
					//boolean done = false;
					//while(!done){
					//	Message message = p.getNextMessage();
					//	if(message instanceOf LocatedCardMessage){
					//		LocatedCardMessage tempMessage = (LocatedCardMessage) message;
					//		if(tempMessage.getLocation().equals(Revealed) && cif.getCardType(tempMessage.getCardName()).equals("Treasure")){
					//			trashedCards.add(player.trashFromDeck(tempMessage.getCardName()));
					//			player.discardTopOfDeck();
					//			done = true;
					//		}
					//	}
					//}
				} else {
					//p.sendMultipleCardMessage(tempList);
					//player.discardTopOfDeck();
					//player.discardTopOfDeck();
					//wait(1000);
				}
				//p.sendRemoveRevealedMessage();
			}
		}
		if(trashedCards.getSize()>0){
			//p.sendMultipleCardMessage(trashedCards);
			//p.sendCreateDoneMessage("Done stealing");
			//while(!Message message = p.getNextMessage() instanceOf DoneMessage && trashedCards.getSize()>0){
				//if(message instanceOf LocatedCardsMessage){
				//	LocatedCardMessage tempMessage = (LocatedCardMessage) message;
				//	if(tempMessage.getLocation().equals("Revealed")){
				//		p.gain(trashedCards.pop(tempMessage.getCardName()));
				//	}
				//}
			//}
		}
		
	}
}
