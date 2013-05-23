package tda367.dominion.server.game.cards;

import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.CardInfoHandler;
import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.GainingHandler;
import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.Supply;

public class Mine extends ChoiceCard {

	public Dominion game;
	public boolean hasTrashed;
	int valueOfTrash;
	
	public Mine() {
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
			} else {
				if (cih.isTreasureCard(card)) {
					p.trashCard(card);
					valueOfTrash = cih.getCardValue(card);
					hasTrashed = true;
				}
			}
		}	
	}

	@Override
	public void play(Dominion game) {
//		TODO: Wiixtor stuff
		this.game = game;
	}
}
