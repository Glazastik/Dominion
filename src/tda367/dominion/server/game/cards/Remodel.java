package tda367.dominion.server.game.cards;

import tda367.dominion.commons.game.CardInfoHandler;
import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.GainingHandler;
import tda367.dominion.server.game.Player;

public class Remodel extends ChoiceCard {
	
	private boolean hasTrashed;
	private int valueOfTrash;
	private CardInfoHandler cih;

	public Remodel(Dominion game) {
		this.game = game;
		state = State.NONACTIVE;
		hasTrashed = false;
		valueOfTrash = 0;
		cih = CardInfoHandler.getInstance();
	}

	@Override
	public void play() {
		game.getActivePlayer().sendTip("Trash a card and then gain a card costing 2 more");
		state = State.ACTIVE;
	}

	@Override
	public void input(Message msg, Player p) {
		if (msg instanceof CardMessage) {
			String card = ((CardMessage) msg).getCard().split("Supply")[0];
			if (!hasTrashed) {
				hasTrashed = true;
				valueOfTrash = cih.getCardValue(card);
				p.trashCard(card);
				game.getActivePlayer().sendTip("Gain a card costing " + (valueOfTrash + 2) + " more");
			} else if(cih.getCardValue(card) <= valueOfTrash+2) {
				GainingHandler gh = new GainingHandler(game.getSupply());					
				gh.playerGainCard(p, card);
				p.sendTip("Continue playing action cards.");
				state = State.NONACTIVE;
			}
		}
	}
}
