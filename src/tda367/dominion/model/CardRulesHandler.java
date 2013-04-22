package tda367.dominion.model;

import java.util.LinkedList;

import tda367.dominion.cards.Councilroom;
import tda367.dominion.cards.Festival;
import tda367.dominion.cards.Laboratory;
import tda367.dominion.cards.Market;
import tda367.dominion.cards.Moat;
import tda367.dominion.cards.Smithy;
import tda367.dominion.cards.Village;
import tda367.dominion.cards.Witch;
import tda367.dominion.cards.Woodcutter;
import tda376.dominion.cardsTest.*;

/**
 * A class that handles how the cards that are played affect the game and players.
 * 
 * @author Grupp 28
 *
 */
public class CardRulesHandler {
	private LinkedList<Player> players;
	private GainingHandler gainingHandler;
	
	/**
	 * Creates a new CardRulesHandler with a list of players 
	 * (see {@link Player}) and a {@link GainingHandler}.
	 * 
	 * @param players the list of active players in the game
	 * @param gainingHandler the GainingHandler that will be used
	 */
	public CardRulesHandler(LinkedList<Player> players,
			GainingHandler gainingHandler) {
		this.gainingHandler = gainingHandler;
		this.players = players;
	}

	/**
	 * Allows the player to play the selected card and be affected by it.
	 * 
	 * @param player the {@link Player} that is playing
	 * @param cardName the card to be played (see {@link tda376.dominion.cardsTest})
	 */
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
