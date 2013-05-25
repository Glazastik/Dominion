package tda367.dominion.server.game.cards;

import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.CardInfoHandler;
import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.GainingHandler;
import tda367.dominion.server.game.Player;

public class Mine extends ChoiceCard {
	
	public boolean hasTrashed;
	int valueOfTrash;
	
	public Mine(Dominion game) {
		this.game = game;
		hasTrashed = false;
		valueOfTrash = 0;
	}
	
	@Override
	public void input(Message msg, Player p) {
		if (msg instanceof CardMessage) {
			String card = ((CardMessage) msg).getCard();
			card = card.split("Supply")[0];
			CardInfoHandler cih = CardInfoHandler.getInstance();
			if (hasTrashed && cih.getCardValue(card) <= valueOfTrash+3) {
				GainingHandler gh = new GainingHandler(game.getSupply());
				gh.playerGainCardToHand(p, card);
				state = State.NONACTIVE;
				p.sendTip("Done, continue playing actions.");
			} else {
				if (cih.isTreasureCard(card)) {
					p.trashCard(card);
					valueOfTrash = cih.getCardValue(card);
					hasTrashed = true;
					p.sendTip("Gain a treasure costing " + (valueOfTrash+3));
				}
			}
		}	
	}

	@Override
	public void play() {
		game.getActivePlayer().sendTip("Trash a treasure from hand then gain a treasure worth up to 3 more.");
		state = State.ACTIVE;
	}
}
