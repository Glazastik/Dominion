package tda367.dominion.cards;

import tda367.dominion.model.CardInfoHandler;
import tda367.dominion.model.Player;

public class Library {

	public static void play(Player p){
		CardInfoHandler cif = CardInfoHandler.getInstance();
		while(p.getHandSize() < 7){
			if(p.revealTopOfDeck() == null){
				break;
			} else if(cif.getCardType(p.revealTopOfDeck()).equals("Action")){
				//p.sendCardMessage(p.revealTopOfDeck())
				//p.createBoolMessage("Set aside?")
				//if(p.getNextMessage().instanceOf(YesNoMessage)){
				//	if((YesNoMessage) p.getNextMessage.isTrue()){
					//	p.draw();
					//} else {
					//	p.setAsideTopOfDeck();
					//}
			} else {
				p.draw();
			}
		}
		p.putRevealedCardsInDiscard();
	}

}
