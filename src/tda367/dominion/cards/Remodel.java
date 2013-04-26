package tda367.dominion.cards;

import tda367.dominion.model.CardInfoHandler;
import tda367.dominion.model.GainingHandler;
import tda367.dominion.model.Player;
import tda367.dominion.model.Supply;

public class Remodel {
	public static void play(Player p, Supply supply){
		GainingHandler gH = new GainingHandler(supply);
		CardInfoHandler cif = CardInfoHandler.getInstance();
		int trashedValue = 0;
		boolean doneTrashing = false;
		boolean doneGaining = false;
		if(p.getHandSize() > 0){
			while(!doneTrashing){
				//p.sendMessage("Trash a card from your hand.");
				//Message tempMessage = p.getNextMessage();
				//LocatedCardMessage tempLCMessage;
				//if(tempMessage instanceOf LocatedCardMessage{
				//tempLCMessage = (LocatedCardMessage) tempMessage;
				//String tempCard = tempLCMessage.getCardName();
				//	if(p.getHand().contains(tempCard){
				//		p.trashCard(tempCard);
				//		trashedValue = cif.getCardValue(tempCard) + 2;
				//		doneTrashing = true
				//	}
				//}
			}
			
			
			
			//if(p.getNextMessage().instanceOf(LocatedCardMessage)){
			//LocatedCardMessage tempMessage = (LocatedCardMessage) p.getNextMessage()
			//Message message = new Message();
			//LocatedCardMessage message = LocatedCardMessage)
			//
			//}
		}
	}
}
