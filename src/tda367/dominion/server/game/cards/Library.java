package tda367.dominion.server.game.cards;

import tda367.dominion.server.game.CardInfoHandler;
import tda367.dominion.server.game.Player;

public class Library {

	public static void play(Player p){
		CardInfoHandler cif = CardInfoHandler.getInstance();
		while(p.getHandSize() < 7){
			if(p.revealTopOfDeck() == null){
				break;
			} else if(cif.getCardType(p.revealTopOfDeck()).equals("Action")){
				//p.sendCardMessage(p.revealTopOfDeck());
				//p.sendInformationMessage("Set aside?");
				//p.createBoolMessage();
				//Message temp = p.getNextMessage();
				//if(temp instanceOf YesNoMessage){
				//	if((YesNoMessage) temp.isTrue()){
					//	p.draw();
					//} else {
					//	p.setAsideTopOfDeck();
					//}
				//p.sendRemoveInstructionMessage();
			} else {
				p.draw();
			}
		}
		//p.sendRemoveRevealedMessages();
		p.putRevealedCardsInDiscard();
	}

}
