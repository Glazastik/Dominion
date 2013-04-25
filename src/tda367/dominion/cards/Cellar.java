package tda367.dominion.cards;

import tda367.dominion.messages.LocatedCardMessage;
import tda367.dominion.model.Player;

public class Cellar {
	public static void play(Player p){
		int amountDiscarded = 0;
		p.increaseActions(1);
		//p.sendMessage("Discard any number of cards and then draw that many cards.")
//		while(p.getNextMessage().instanceOf(doneMessage) || p.getHandSize() == 0){
//			if(p.getNextMessage().instanceOf(LocatedCardMessage)){
//				LocatedCardMessage temp = (LocatedCardMessage)p.getNextMessage();
//				if((temp.getLocation.equals("Hand") && p.getHand().contains(temp.getCardName()){
//					p.discardCard(temp.getCardName());
//					amountDiscarded++;
//				}
//			}
//		}
		p.draw(amountDiscarded);
	}
}
