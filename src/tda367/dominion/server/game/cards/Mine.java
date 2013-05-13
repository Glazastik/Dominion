package tda367.dominion.server.game.cards;

import tda367.dominion.server.game.CardInfoHandler;
import tda367.dominion.server.game.GainingHandler;
import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.Supply;

public class Mine {

	public static void play(Player p, Supply supply){
		int trashedValue = 0;
		boolean doneTrashing = false;
		boolean doneGaining = false;
		CardInfoHandler cif = CardInfoHandler.getInstance();
		GainingHandler gH = new GainingHandler(supply);
		if(p.getHand().contains("Copper") || p.getHand().contains("Silver") || p.getHand().contains("Gold") ){
			//while(!doneTrashing){
				//p.sendInformationMessage("Trash a treasure from you hand.")
				//Message temp = p.getNextMessage();
				//if(temp instanceOf LocatedCardMessage){
					//LocatedCardMessage tempMessage = (LocatedCardMessage) p.getNextMessage();
					//String tempCard = tempMessage.getCardName;
					//if(cif.getCardType(tempCard).equals("Treasure") && tempMessage.getLocation().equals("Hand")){
						//switch(tempCard){
							//case "Copper"; p.trashCard("Copper"); trashedValue = 0; break;
							//case "Silver"; p.trashCard("Silver"); trashedValue = 3; break;
							//case "Gold"; p.trashCard("Gold"); trashedValue = 6; break;
						//}
					//doneTrashing = true;
					//}
				//}
				//p.removeInformationMessage();
			
			//}
			
			//while(!doneGaining){
				//p.sendInformationMessage("Gain a card costing up to 3 more.")
				//if(p.getNextMessage().instanceOf(LocatedCardMessage)){
					//LocatedCardMessage temp = (LocatedCardMessage) p.getNextMessage();
					//tempCard = temp.getCardName();
					//if(cif.getCardType(tempCard).equals("Treasure") && gH.isCardGainable(tempCard, 3 + trashedValue){
						//gH.playerGainCardToHand(p, tempCard);
						//doneGaining = true;
					//}
				//}
				//p.removeInformationMessage();
			//}
			
		}
	}
}