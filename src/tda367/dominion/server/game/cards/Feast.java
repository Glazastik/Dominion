package tda367.dominion.server.game.cards;

import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.CardInfoHandler;
import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.GainingHandler;
import tda367.dominion.server.game.Player;

public class Feast extends ChoiceCard {
	
	private Dominion game; 

	public Feast(Dominion game) {
		state = State.NONACTIVE;
	}

	@Override
	public void play() {
		this.game = game;
		state = State.ACTIVE;

		game.getActivePlayer().trashFromPlayingArea("Feast");
		game.getActivePlayer().sendTip("Gain a card costing up to 5");

	}

	@Override
	public void input(Message msg, Player p) {
		if (msg instanceof CardMessage) {
			String card = ((CardMessage) msg).getCard().split("Supply")[0];
			CardInfoHandler cih = CardInfoHandler.getInstance();
			if (cih.getCardValue(card) <= 5) {
				GainingHandler gh = new GainingHandler(game.getSupply());
				gh.playerGainCard(p, card);
				state = State.NONACTIVE;
			}
		}
	}
}
