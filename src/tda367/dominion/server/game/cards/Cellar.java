package tda367.dominion.server.game.cards;

import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.messages.DoneMessage;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.Player;

public class Cellar implements ChoiceCard {
	public State state;
	public int amountDiscarded;
	
	public Cellar() {
		state = State.NONACTIVE;
		amountDiscarded = 0;
	}

	@Override
	public void play(Player p) {
		
		state = State.ACTIVE;
		p.increaseActions(1);
		
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
			if (p.getHandSize() == 0) {
				input(new DoneMessage(), p);
			}
			
			//TODO:Send messgae
		}
	}
	
	@Override
	public boolean isActive() {
		if (state == State.ACTIVE) {
			return true;
		} else {
			return false;
		}
	}
}
