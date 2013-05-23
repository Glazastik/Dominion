package tda367.dominion.server.game.cards;

import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.CardInfoHandler;
import tda367.dominion.server.game.GainingHandler;
import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.Supply;

public class Workshop implements ChoiceCard {

	public State state;
	private Supply supply;

	public Workshop(Supply s) {
		state = State.NONACTIVE;
		this.supply = s;
	}

	@Override
	public void play(Player p) {
		state = State.ACTIVE;
	}

	@Override
	public void input(Message msg, Player p) {
		if (msg instanceof CardMessage) {
			CardInfoHandler cih = CardInfoHandler.getInstance();
			GainingHandler gh = new GainingHandler(supply);
			if (cih.getCardValue(((CardMessage) msg).getCard()) < 5) {
				gh.playerGainCard(p, ((CardMessage) msg).getCard());
				state = State.NONACTIVE;
			}
		}

	}

	@Override
	public boolean isActive() {
		if (state == State.ACTIVE) {
			return false;
		} else {
			return false;
		}
	}

}
