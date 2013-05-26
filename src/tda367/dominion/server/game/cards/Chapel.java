package tda367.dominion.server.game.cards;

import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.messages.DoneMessage;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.Player;

public class Chapel extends ChoiceCard {

	public int amountTrashed;

	public Chapel(Dominion game) {
		this.game = game;
		state = State.NONACTIVE;
		amountTrashed = 0;
	}

	@Override
	public void play() {
		state = State.ACTIVE;
		game.getActivePlayer().sendTip("You may trash up to 4 more cards or press 'Done'.");
	}

	@Override
	public void input(Message msg, Player p) {
		if (msg instanceof DoneMessage) {

			p.sendTip("Continue action phase.");
			state = State.NONACTIVE;

		} else if (msg instanceof CardMessage) {

			p.trashCard(((CardMessage) msg).getCard());
			amountTrashed++;
			p.sendTip("You may trash up to "+ (4-amountTrashed) + " more cards or press 'Done'.");

			if (amountTrashed == 4) {
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
