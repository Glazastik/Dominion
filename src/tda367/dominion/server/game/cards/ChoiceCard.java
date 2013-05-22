package tda367.dominion.server.game.cards;

import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.Player;

public interface ChoiceCard extends ICard {

	public void play (Player p);
	
	public void input (Message msg, Player p);
	
}
