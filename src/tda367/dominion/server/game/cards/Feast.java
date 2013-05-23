package tda367.dominion.server.game.cards;

import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.GainingHandler;
import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.Supply;

public class Feast extends ChoiceCard {
	
	public Supply supply;
	
	public Feast(Supply s) {
		state = State.NONACTIVE;
		supply = s;
	}
	
	public void play(Player p) {

		state = State.ACTIVE;
		
		p.trashFromPlayingArea("Feast");
		
	}

	public void input(Message msg, Player p) {
		if (msg instanceof CardMessage) {
			GainingHandler gh = new GainingHandler(supply);
			gh.playerGainCard(p, ((CardMessage) msg).getCard());
			state = State.NONACTIVE;
		}
	}
}
