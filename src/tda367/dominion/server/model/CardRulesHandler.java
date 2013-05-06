package tda367.dominion.server.model;

import java.util.LinkedList;

import tda367.dominion.server.cards.Adventurer;
import tda367.dominion.server.cards.Bureaucrat;
import tda367.dominion.server.cards.Cellar;
import tda367.dominion.server.cards.Chancellor;
import tda367.dominion.server.cards.Chapel;
import tda367.dominion.server.cards.Councilroom;
import tda367.dominion.server.cards.Feast;
import tda367.dominion.server.cards.Festival;
import tda367.dominion.server.cards.Laboratory;
import tda367.dominion.server.cards.Library;
import tda367.dominion.server.cards.Market;
import tda367.dominion.server.cards.Militia;
import tda367.dominion.server.cards.Mine;
import tda367.dominion.server.cards.Moat;
import tda367.dominion.server.cards.Moneylender;
import tda367.dominion.server.cards.Remodel;
import tda367.dominion.server.cards.Smithy;
import tda367.dominion.server.cards.Spy;
import tda367.dominion.server.cards.Thief;
import tda367.dominion.server.cards.Throneroom;
import tda367.dominion.server.cards.Village;
import tda367.dominion.server.cards.Witch;
import tda367.dominion.server.cards.Woodcutter;
import tda367.dominion.server.cards.Workshop;

/**
 * A class that handles how the cards that are played affect the game and players.
 * 
 * @author Grupp 28
 *
 */
public class CardRulesHandler {
	private LinkedList<Player> players;
	private Supply supply;
	
	/**
	 * Creates a new CardRulesHandler with a list of players 
	 * (see {@link Player}) and a {@link GainingHandler}.
	 * 
	 * @param players the list of active players in the game
	 * @param gainingHandler the GainingHandler that will be used
	 */
	public CardRulesHandler(LinkedList<Player> players,
			Supply supply) {
		this.supply = supply;
		this.players = players;
	}

	/**
	 * Allows the player to play the selected card and be affected by it.
	 * 
	 * @param player the {@link Player} that is playing
	 * @param cardName the card to be played (see {@link tda367.dominion.cardsTest})
	 */
	public void playCard(Player player, String cardName) {
		player.play(cardName);
		switch(cardName){
			case "Adventurer": Adventurer.play(player); break;
			case "Bureaucrat": Bureaucrat.play(player, players, supply); break;
			case "Cellar": Cellar.play(player); break;
			case "Chancellor": Chancellor.play(player); break;
			case "Chapel": Chapel.play(player); break;
			case "Copper": player.increaseMoney(1); break;
			case "Councilroom": Councilroom.play(player, players); break;
			case "Feast": Feast.play(player, supply); break;
			case "Festival": Festival.play(player); break;
			case "Gold": player.increaseMoney(3); break;
			case "Laboratory": Laboratory.play(player); break;
			case "Library": Library.play(player); break;
			case "Market": Market.play(player); break;
			case "Militia": Militia.play(player, players); break;
			case "Mine": Mine.play(player, supply); break;
			case "Moat": Moat.play(player); break;
			case "Moneylender": Moneylender.play(player); break;
			case "Remodel": Remodel.play(player, supply); break;
			case "Silver": player.increaseMoney(2); break;
			case "Smithy": Smithy.play(player); break;
			case "Spy": Spy.play(player, players); break;
			case "Thief": Thief.play(player, players); break;
			case "Throneroom": Throneroom.play(player, players, supply); break;
			case "Village": Village.play(player); break;
			case "Witch": Witch.play(player, players, supply); break;
			case "Woodcutter": Woodcutter.play(player); break;
			case "Workshop": Workshop.play(player, supply); break;
		}
	}
}
