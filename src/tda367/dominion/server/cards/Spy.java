package tda367.dominion.server.cards;

import java.util.LinkedList;

import tda367.dominion.server.game.Player;

public class Spy {
	public static void play(Player player, LinkedList<Player> players){
		for(Player p: players){
			if(p.getHand().contains("Moat")){
				Spy(player, p);
			} else {
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
				 * 			Spy(player, p);
				 * 		}
				 * 	}
				 * }
				 * p.removeInformationMessage();
				 * p.removeBoolMessage(); 
				 */
			}
		}
	}
	private static void Spy(Player player, Player p){
		//player.sendCardMessage(player.revealTopOfDeck());
		//player.createBoolMessage("Discard card?");
		//Message message = p.getNextMessage();
		//if(message instanceOf YesNoMessage){
		//YesNoMessage tempMessage = (YesNoMessage) message;
		//if(tempMessage.isTrue()){
			//p.discardTopOfDeck();
		//}
		//player.sendRemoveRevealedMessage();
		//player.sendRemoveBoolMessage();
	}
}
