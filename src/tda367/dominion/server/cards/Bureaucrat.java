package tda367.dominion.server.cards;

import java.util.LinkedList;

import tda367.dominion.server.game.CardInfoHandler;
import tda367.dominion.server.game.GainingHandler;
import tda367.dominion.server.game.Pile;
import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.Supply;

public class Bureaucrat {
	public static void play(Player player, LinkedList<Player> players, Supply supply){
		GainingHandler gH = new GainingHandler(supply);
		CardInfoHandler cif = CardInfoHandler.getInstance();
		boolean hasVictoryCard = false;
		if(gH.isCardGainable("Silver")){
			player.putOnTopOfDeck(supply.take("Silver"));
		}
		for(Player p : players){
			hasVictoryCard = false;
			if(p!=player){
				Pile hand = p.getHand();
				for(String card : hand.getCards()){
					if(cif.getCardType(card).equals("Victory")){
						hasVictoryCard = true;
					}
				}
				if(hasVictoryCard && !p.getHand().contains("Moat")){
					Bureaucrat(player, p);
				} else if(hasVictoryCard && p.getHand().contains("Moat")){
					/**
					 * p.sendInformationMessage("Do you wish to reveal Moat?");
					 * p.createBoolMessage();
					 * boolean done = false;
					 * while(!done){
					 * 	Message message = p.getNextMessage();
					 * 	if(message instanceOf BoolMessage){
					 * 		done = true;
					 * 		BoolMessage boolMessage = (BoolMessage) message;
					 * 		if(!boolMessage.isTrue()){
					 * 			Bureaucrat(player,p);
					 * 		}
					 * 	}
					 * }
					 * p.removeInformationMessage();
					 * p.removeBoolMessage(); 
					 */
				} else {
					//player.sendMultipleCardMessage(p.getHand().getCards());
					//wait(1000);
					//player.removeMultipleCardMessage();
				}
			}
		}	
	}
	private static void Bureaucrat(Player player, Player p){
		CardInfoHandler cif = CardInfoHandler.getInstance();
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
	}
}
