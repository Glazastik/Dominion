package tda367.dominion.server.game.cards;

import tda367.dominion.commons.messages.BoolMessage;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.Player;

/**
 * This class executes the effects of chancellor, the player who played this will receive 
 * a choice whether to discard his whole deck or not.
 * 
 * This is one of the calsses that uses the YesNoBox in the InGameState.
 * 
 * @author gti
 *
 */
public class Chancellor extends ChoiceCard {
	
	public Chancellor(Dominion game) {
		this.game = game;
	}

	public void play() {
		game.getActivePlayer().increaseMoney(2);
		game.activateYesNoBox("Do you wish to discard your deck?");
		state = State.ACTIVE;		
	}

	@Override
	public void input(Message msg, Player p) {
		if (msg instanceof BoolMessage) {
			if (((BoolMessage) msg).isTrue()) {
				p.discardDeck();
			}
			p.sendTip("Continue playing action cards.");
			state = State.NONACTIVE;
		}
	}
}
