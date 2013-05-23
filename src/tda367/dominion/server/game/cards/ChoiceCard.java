package tda367.dominion.server.game.cards;

import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.Player;

public abstract class ChoiceCard implements ICard {
	
	protected State state;
	
	public boolean isActive() {
		return state == State.ACTIVE;
	}
	
	public void play() {
		state = State.ACTIVE;
	}
	
	public abstract void input (Message msg, Player p);
}
