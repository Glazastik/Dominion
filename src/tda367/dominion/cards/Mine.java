package tda367.dominion.cards;

import tda367.dominion.model.CardInfoHandler;
import tda367.dominion.model.GainingHandler;
import tda367.dominion.model.Player;
import tda367.dominion.model.Supply;

public class Mine {

	public static void play(Player p, Supply supply){
		int trashedValue = 0;
		CardInfoHandler cif = CardInfoHandler.getInstance();
		GainingHandler gH = new GainingHandler(supply);
		if(p.getHand().contains("Copper") || p.getHand().contains("Silver") || p.getHand().contains("Gold") ){
			//p.sendMessage("Trash a treasure from you hand.")
			//if(p.getNextMessage().instanceOf(LocatedCardMessage)){
			//LocatedCardMessage temp = (LocatedCardMessage) p.getNextMessage();
			
			//String tempCard = temp.getCardName;
				//if(cif.getCardType(tempCard).equals("Treasure"){
					//switch(tempCard){
					//case "Copper"; p.trashCard("Copper"); trashedValue = 0; break;
					//case "Silver"; p.trashCard("Silver"); trashedValue = 3; break;
					//case "Gold"; p.trashCard("Gold"); trashedValue = 6; break;/			
				//}
			//}
			
			//p.sendMessage("Gain a card costing up to 3 more.")
			//if(p.getNextMessage().instanceOf(LocatedCardMessage)){
			//LocatedCardMessage temp = (LocatedCardMessage) p.getNextMessage();
			//tempCard = temp.getCardName();
			//if(cif.getCardType(tempCard).equals("Treasure") && gH.isCardGainable(tempCard, 3 + trashedValue){
			//gH.playerGainCard(p, tempCard);
			//}
			
		}
	}
}