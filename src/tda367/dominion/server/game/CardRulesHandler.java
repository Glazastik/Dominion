package tda367.dominion.server.game;

import java.util.LinkedList;

import tda367.dominion.server.game.cards.*;

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
		CardInfoHandler cih = CardInfoHandler.getInstance(); 
		if((cih.getCardType(cardName).equals("Action") && player.getActions()>0) || cih.getCardType(cardName).equals("Treasure")) {
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
	
	/**
	 * Plays all treasures from selected players hand
	 * @param player
	 */
	public void playAllTreasures(Player player) {
		CardInfoHandler cih = CardInfoHandler.getInstance(); 
		for(int i = 0; i<player.getHandSize(); i++) {
			if(cih.getCardType(player.getHand().getCard(i)).equals("Treasure")) {
				switch(player.getHand().getCard(i)) {
					case "Copper": player.increaseMoney(1); break;
					case "Silver": player.increaseMoney(2); break;
					case "Gold": player.increaseMoney(3); break;
				}
				player.play(i);
				i--;
			}
		}
	}
}