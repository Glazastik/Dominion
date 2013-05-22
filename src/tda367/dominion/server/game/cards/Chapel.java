package tda367.dominion.server.game.cards;

import tda367.dominion.commons.messages.Message;
import tda367.dominion.commons.messages.*;
import tda367.dominion.server.game.Player;

public class Chapel implements ChoiceCard {

	public enum State { ACTIVE, NONACTIVE }
	public State state;
	public int amountTrashed;
	
	public Chapel() {
		state = State.NONACTIVE;
		amountTrashed = 0;
	}
	
	public void play(Player p) {
		
		state = State.ACTIVE;
		
		//TODO: Send some kind of message
	}
	
	public void input(Message msg, Player p) {
		if (msg instanceof DoneMessage) {
			
			state = State.NONACTIVE;
			//TODO: Sedn mesgagew
			
		} else if (msg instanceof CardMessage) {
			
			p.trashCard(((CardMessage) msg).getCard());
			amountTrashed++;
			
			if (amountTrashed == 4) {
				input(new DoneMessage(), null);
			} else {
				//TODO: Send some other message
			}
			
		}
	}

}
