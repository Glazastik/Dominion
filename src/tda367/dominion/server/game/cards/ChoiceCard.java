package tda367.dominion.server.game.cards;

import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.Player;

public abstract class ChoiceCard implements ICard {
	private State state;
	
	public boolean isActive() {
		return state == State.ACTIVE;
	}
	
	public void play (Player p) {
		state = State.ACTIVE;
	}
	
	public abstract void input (Message msg, Player p);
}
