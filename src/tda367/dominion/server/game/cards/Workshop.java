package tda367.dominion.server.game.cards;

import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.CardInfoHandler;
import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.GainingHandler;
import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.Supply;

public class Workshop extends ChoiceCard {

	private Dominion game;
	
	public Workshop(Dominion game) {
		this.game = game;
		state = State.NONACTIVE;
	}

	public void play() {
		state = State.ACTIVE;
		game.getActivePlayer().sendTip("Gain a card costing up to 3");
	}

	public void input(Message msg, Player p) {
		if (msg instanceof CardMessage) {
			String card = ((CardMessage) msg).getCard().split("Supply")[0];
			CardInfoHandler cih = CardInfoHandler.getInstance();
			GainingHandler gh = new GainingHandler(game.getSupply());
			if (cih.getCardValue(card) < 5) {
				gh.playerGainCard(p, card);
				state = State.NONACTIVE;
				//Temporary tip
				p.sendTip("Continua playing actions!");
			}
		}
	}
}
