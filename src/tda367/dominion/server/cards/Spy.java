package tda367.dominion.server.cards;

import java.util.LinkedList;

import tda367.dominion.server.model.Player;

public class Spy {
	public static void play(Player p, LinkedList<Player> players){
		for(Player player: players){
			//p.sendCardMessage(player.revealTopOfDeck());
			//p.createBoolMessage("Discard card?");
			//Message message = p.getNextMessage();
			//if(message instanceOf YesNoMessage){
			//YesNoMessage tempMessage = (YesNoMessage) message;
			//if(tempMessage.isTrue()){
				//player.discardTopOfDeck();
			//}
			//p.sendRemoveRevealedMessage();
			//p.sendRemoveBoolMessage();
		}
	}
}
