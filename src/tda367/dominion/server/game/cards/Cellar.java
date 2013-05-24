package tda367.dominion.server.game.cards;

import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.messages.DoneMessage;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.Player;

public class Cellar extends ChoiceCard {
	
	public int amountDiscarded;
	
	public Cellar(Dominion game) {
		this.game = game;
		state = State.NONACTIVE;
		amountDiscarded = 0;
	}

	@Override
	public void play() {
		
		state = State.ACTIVE;
		game.getActivePlayer().increaseActions(1);
		game.getActivePlayer().sendTip("Discard up to 4 cards from hand or press done!");
	}

	@Override
	public void input(Message msg, Player p) {
		if (msg instanceof DoneMessage) {
			p.draw(amountDiscarded);
			//Send message: Player drew x cards.
			state = State.NONACTIVE;
		} else if (msg instanceof CardMessage) {
			p.discardCard(((CardMessage) msg).getCard());
			amountDiscarded++;
			p.sendTip("Discard up to " + (4-amountDiscarded) + " cards from hand or press done!");
			if (p.getHandSize() == 0) {
				input(new DoneMessage(), p);
			}
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
