package tda367.dominion.server.game.cards;

import tda367.dominion.commons.messages.Message;
import tda367.dominion.commons.messages.*;
import tda367.dominion.server.game.Player;

public class Chapel extends ChoiceCard {

	public int amountTrashed;

	public Chapel() {
		state = State.NONACTIVE;
		amountTrashed = 0;
	}

	public void play(Player p) {

		state = State.ACTIVE;

		// Send message: Trash 4 cards or done.
	}

	public void input(Message msg, Player p) {
		if (msg instanceof DoneMessage) {

			state = State.NONACTIVE;

		} else if (msg instanceof CardMessage) {

			p.trashCard(((CardMessage) msg).getCard());
			amountTrashed++;
			// Send: Player trashed msg.getCard

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
