package tda367.dominion.cards;

import java.util.LinkedList;

import tda367.dominion.model.CardInfoHandler;
import tda367.dominion.model.GainingHandler;
import tda367.dominion.model.Pile;
import tda367.dominion.model.Player;
import tda367.dominion.model.Supply;

public class Bureaucrat {
	public static void play(Player player, LinkedList<Player> players, Supply supply){
		GainingHandler gH = new GainingHandler(supply);
		CardInfoHandler cif = CardInfoHandler.getInstance();
		if(gH.isCardGainable("Silver")){
			player.putOnTopOfDeck(supply.take("Silver"));
		}
		for(Player p : players){
			boolean hasVictoryCard = false;
			if(p!=player){
				Pile hand = p.getHand();
				for(String card : hand.getCards()){
					if(cif.getCardType(card).equals("Victory")){
						hasVictoryCard = true;
					}
				}
				if(hasVictoryCard){
					boolean done = false;
					//p.sendInformationMessage("Chose a victory card to put on top of your deck.");
					while(!done){
						//Message message = p.getNextMessage();
						//if(message instanceOf LocatedCardMessage){
							//LocatedCardMessage tempMessage = (LocatedCardMessage) message;
							//if(tempMessage.getLocation().equals("Hand") && cif.getCardType(tempMessage.getCardName().equals("Victory"))){
								//p.putOnTopOfDeck(p.getHand().pop(tempMessage.getCardName());)
								//done = true;
							//}
						//}
					}
					//p.removeInformationMessage();
				} else {
					//player.sendMultipleCardMessage(p.getHand().getCards());
					//wait(1000);
					//player.removeMultipleCardMessage();
				}
			}
		}
	}
}
