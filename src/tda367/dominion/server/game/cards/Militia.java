package tda367.dominion.server.game.cards;

import java.util.LinkedList;

import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.Player;

public class Militia extends ChoiceCard {
	private LinkedList<Player> players;
	public Militia (LinkedList<Player> players){
		this.players = players;
	}

	public void play(Player player){
		state = State.ACTIVE;
		player.increaseMoney(2);
		for(Player p: players){
			if(player!=p){
				if(!p.getHand().contains("Moat")){
					//Militia(p);
					if(p.getHandSize()>3){
						p.sendTip("Discard down to 3 cards");
					}
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

	@Override
	public void input(Message msg, Player p) {
		if(msg instanceof CardMessage && p.getHandSize()>3){
			if(p.containsCard(((CardMessage) msg).getCard()){
				p.discardCard(((CardMessage) msg).getCard());
			}
		}
		
	}

}
