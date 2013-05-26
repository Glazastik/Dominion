package tda367.dominion.server.game.cards;

import tda367.dominion.commons.game.CardInfoHandler;
import tda367.dominion.commons.messages.BoolMessage;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.Player;

public class Library extends ChoiceCard {
	private Player player;
	private CardInfoHandler cif = CardInfoHandler.getInstance();

	public Library(Dominion game) {
		this.game = game;
		state = State.NONACTIVE;
	}

	public void play() {
		player = game.getActivePlayer();
		state = State.ACTIVE;
		while (player.getHandSize() < 7) {
			if (player.revealTopOfDeck() == null) {
				state = State.NONACTIVE;
				break;
			} else if (cif.getCardType(player.revealTopOfDeck()).equals(
					"Action")) {
				game.activateYesNoBox("Set aside: " + player.revealTopOfDeck()
						+ "?");
				break;
			} else {
				player.draw();
			}
		}
		if (player.revealTopOfDeck() == null || player.getHandSize() >= 7) {
			player.putRevealedCardsInDiscard();
			state = State.NONACTIVE;
		}
	}

	@Override
	public void input(Message msg, Player p) {
		if (msg instanceof BoolMessage) {
			if (!((BoolMessage) msg).isTrue()) {
				p.draw();
			} else {
				p.discardTopOfDeck();
			}
		}
		while (player.getHandSize() < 7) {
			if (player.revealTopOfDeck() == null) {
				state = State.NONACTIVE;
				break;
			} else if (cif.getCardType(player.revealTopOfDeck()).equals(
					"Action")) {
				game.activateYesNoBox("Set aside: " + player.revealTopOfDeck()
						+ "?");
				break;
			} else {
				player.draw();
			}
		}
	}
}
