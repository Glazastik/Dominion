package tda367.dominion.server.cards;

import tda367.dominion.server.model.CardInfoHandler;
import tda367.dominion.server.model.GainingHandler;
import tda367.dominion.server.model.Player;
import tda367.dominion.server.model.Supply;

public class Remodel {
	public static void play(Player p, Supply supply){
		GainingHandler gH = new GainingHandler(supply);
		CardInfoHandler cif = CardInfoHandler.getInstance();
		int trashedValue = 0;
		boolean doneTrashing = false;
		boolean doneGaining = false;
		if(p.getHandSize() > 0){
			while(!doneTrashing){
				//p.sendInformationMessage("Trash a card from your hand.");
				//Message tempMessage = p.getNextMessage();
				//LocatedCardMessage tempLCMessage;
				//if(tempMessage instanceOf LocatedCardMessage{
					//tempLCMessage = (LocatedCardMessage) tempMessage;
					//String tempCard = tempLCMessage.getCardName();
					//if(p.getHand().contains(tempCard) && tempLCMessage.getLocation().equals("Hand")){
						//p.trashCard(tempCard);
						//trashedValue = cif.getCardValue(tempCard) + 2;
						//doneTrashing = true
					//}
				//}
				//p.removeInformationMessage();
			}
			while(!doneGaining){
				//p.sendInformationMessage("Gain a card costing up to " + trashedValue);
				//Message tempMessage = p.getNextMessage();
				//LocatedCardMessage tempLCMessage;
				//if(tempMessage instanceOf LocatedCardMessage{
					//tempLCMessage = (LocatedCardMessage) tempMessage;
					//String tempCard = tempLCMessage.getCardName();
					//if(gH.isCardGainable(tempCard) && tempLCMessage.getLocation().equals("Supply")){
						//gH.playerGainCard(p, tempCard);
						//doneGaining = true;
					//}
				//}
				//p.removeInformationMessage();
			}
		}
	}
}
