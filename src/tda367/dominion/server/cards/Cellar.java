package tda367.dominion.server.cards;

import tda367.dominion.server.game.Player;

public class Cellar {
	public static void play(Player p){
		int amountDiscarded = 0;
		p.increaseActions(1);
		//p.sendInstructionMessage("Discard any number of cards and then draw that many cards.")
		//p.createDoneMessage();
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
//		p.removeInstructionMessage();
//		p.removeDoneMessage();
		p.draw(amountDiscarded);
	}
}
