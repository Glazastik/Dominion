package tda367.dominion.server.game.cards;

import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.messages.DoneMessage;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.Player;

public class Cellar implements ChoiceCard {
	public enum State { ACTIVE, NONACTIVE }
	public State state;
	public int amountDiscarded;
	
	public Cellar() {
		state = State.NONACTIVE;
		amountDiscarded = 0;
	}
//	public static void play(Player p){
//		int amountDiscarded = 0;
//		p.increaseActions(1);
		//p.sendInstructionMessage("Discard any number of cards and then draw that many cards.")
		//p.createDoneMessage();
//		Message temp = p.getNextMessage();
//		while(!temp instanceOf doneMessage || p.getHandSize() == 0){
//			if(temp.instanceOf(LocatedCardMessage)){
//				LocatedCardMessage tempMessage = (LocatedCardMessage) temp;
//				if((tempMessage.getLocation.equals("Hand") && p.getHand().contains(tempMessage.getCardName()){
//					p.discardCard(tempMessage.getCardName());
//					amountDiscarded++;
//				}
//			}
//		}
//		p.removeInstructionMessage();
//		p.removeDoneMessage();
//		p.draw(amountDiscarded);
//	}

	@Override
	public void play(Player p) {
		
		state = State.ACTIVE;
		
		//TODO:Send some kind of message
	}

	@Override
	public void input(Message msg, Player p) {
		if (msg instanceof DoneMessage) {
			p.draw(amountDiscarded);
			state = State.NONACTIVE;
			
			//TODO:Sned Mssegase
		} else if (msg instanceof CardMessage) {
			p.discardCard(((CardMessage) msg).getCard());
			amountDiscarded++;
			
			//TODO:Send messgae
		}
	}
}
