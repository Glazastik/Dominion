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
	
	@Override
	public void play(Player p) {

		state = State.ACTIVE;
		
		p.trashFromPlayingArea("Feast");
		
	}

	@Override
	public void input(Message msg, Player p) {
		if (msg instanceof CardMessage) {
			String card = ((CardMessage) msg).getCard().split("Supply")[0];
			GainingHandler gh = new GainingHandler(supply);
			gh.playerGainCard(p, card);
			state = State.NONACTIVE;
		}
	}
}
