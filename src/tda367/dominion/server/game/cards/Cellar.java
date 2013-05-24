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
		game.getActivePlayer().sendTip("Discard any number of cards in hand and then draw that many or press done!");
	}

	@Override
	public void input(Message msg, Player p) {
		if (msg instanceof DoneMessage) {
			p.draw(amountDiscarded);
			p.sendTip("Done, continue playing actions.");
			p.sendLog(p.getName() + " drew " + amountDiscarded + "cards with cellar.");
			state = State.NONACTIVE;
		} else if (msg instanceof CardMessage) {
			p.discardCard(((CardMessage) msg).getCard());
			amountDiscarded++;
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
