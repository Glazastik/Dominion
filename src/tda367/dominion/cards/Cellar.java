package tda367.dominion.cards;

import tda367.dominion.messages.LocatedCardMessage;
import tda367.dominion.model.Player;

public class Cellar {
	public static void play(Player p){
		int amountDiscarded = 0;
		p.increaseActions(1);
		//p.sendMessage("Discard any number of cards and then draw that many cards.")
//		Message temp = p.getNextMessage();
//		while(!temp instanceOf doneMessage || p.getHandSize() == 0){
//			if(temp.instanceOf(LocatedCardMessage)){
//				LocatedCardMessage tempMessage = (LocatedCardMessage) temp;
//				if((tempMessage.getLocation.equals("Hand") && p.getHand().contains(tempMessage.getCardName()){
//					p.discardCard(tempMessage.getCardName());
//					amountDiscarded++;
//				}
//			}
//		}
		p.draw(amountDiscarded);
	}
}
