package tda367.dominion.server.game.cards;

import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.CardInfoHandler;
import tda367.dominion.server.game.GainingHandler;
import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.Supply;

public class Mine extends ChoiceCard {

	public Supply supply;
	public boolean hasTrashed;
	int valueOfTrash;
	
	public Mine(Supply s) {
		supply = s;
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
				GainingHandler gh = new GainingHandler(supply);
				gh.playerGainCardToHand(p, card);
				state = State.NONACTIVE;
			} else {
				if (cih.isTreasureCard(card)) {
					p.trashCard(card);
					valueOfTrash = cih.getCardValue(card);
					hasTrashed = true;
				}
			}
		}	
	}
}
