package tda376.dominion.model;

import java.util.LinkedList;

import tda376.dominion.cards.*;

public class CardRulesHandler {
	private LinkedList<Player> players;
	private GainingHandler gainingHandler;

	public CardRulesHandler(LinkedList<Player> players,
			GainingHandler gainingHandler) {
		this.gainingHandler = gainingHandler;
		this.players = players;
	}

	public void playCard(Player player, String cardName) {
		player.play(cardName);
		if (cardName.equals("Smithy")) {
			Smithy.play(player);
		} else if (cardName.equals("Village")) {
			Village.play(player);
		} else if (cardName.equals("Councilroom")) {
			Councilroom.play(player, players);
		} else if (cardName.equals("Laboratory")) {
			Laboratory.play(player);
		} else if (cardName.equals("Market")) {
			Market.play(player);
		} else if (cardName.equals("Festival")) {
			Festival.play(player);
		} else if (cardName.equals("Witch")) {
			Witch.play(player, players, gainingHandler);
		} else if (cardName.equals("Woodcutter")) {
			Woodcutter.play(player);
		} else if (cardName.equals("Moat")) {
			Moat.play(player);
		}
	}
}
