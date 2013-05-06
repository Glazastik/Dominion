package tda367.dominion.server.cards;

import java.util.LinkedList;

import tda367.dominion.server.model.Player;

public class Militia {

	public static void play(Player player, LinkedList<Player> players){
		player.increaseMoney(2);
		for(Player p: players){
			if(player!=p){
				if(p.getHand().contains("Moat")){
					Militia(p);
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
					 * 			Militia(p);
					 * 		}
					 * 	}
					 * }
					 * p.removeInformationMessage();
					 * p.removeBoolMessage(); 
					 */
				}
			}
		}
	}
	private static void Militia(Player p){
		while(p.getHandSize()>3){
			//p.sendInformationMessage("Discard down to 3 cards");
			//Message temp = p.getNextMessage
			//if(temp instanceOf LocatedCardMessage){
				//LocatedCardMessage tempMessage = (LocatedCardMessage) temp;
				//if(p.getHand().contains(tempMessage.getCard()) && tempMessage.getLocation().equals("Hand")){
					//p.discardCard((LocatedCardMessage) temp.getCard)
				//}
			//}
			//p.removeInformationMessage();
		}
	}

}
