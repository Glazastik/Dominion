package tda367.dominion.server.main;

import tda367.dominion.commons.game.CardInfoHandler;
import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.GainingHandler;
import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.cards.Adventurer;
import tda367.dominion.server.game.cards.Bureaucrat;
import tda367.dominion.server.game.cards.Cellar;
import tda367.dominion.server.game.cards.Chancellor;
import tda367.dominion.server.game.cards.Chapel;
import tda367.dominion.server.game.cards.ChoiceCard;
import tda367.dominion.server.game.cards.Councilroom;
import tda367.dominion.server.game.cards.Feast;
import tda367.dominion.server.game.cards.Festival;
import tda367.dominion.server.game.cards.Laboratory;
import tda367.dominion.server.game.cards.Library;
import tda367.dominion.server.game.cards.Market;
import tda367.dominion.server.game.cards.Militia;
import tda367.dominion.server.game.cards.Mine;
import tda367.dominion.server.game.cards.Moat;
import tda367.dominion.server.game.cards.Moneylender;
import tda367.dominion.server.game.cards.Remodel;
import tda367.dominion.server.game.cards.Smithy;
import tda367.dominion.server.game.cards.Spy;
import tda367.dominion.server.game.cards.Village;
import tda367.dominion.server.game.cards.Witch;
import tda367.dominion.server.game.cards.Woodcutter;
import tda367.dominion.server.game.cards.Workshop;

/**
 * A class that handles how the cards that are played affect the game and players.
 * 
 * @author Grupp 28
 *
 */
public class CardRulesHandler {
	private Dominion game;
	public ChoiceCard activeCard;
	
	/**
	 * Creates a new CardRulesHandler with a list of players 
	 * (see {@link Player}) and a {@link GainingHandler}.
	 * 
	 * @param players the list of active players in the game
	 * @param gainingHandler the GainingHandler that will be used
	 */
	public CardRulesHandler(Dominion game) {
		this.game = game;
	}

	/**
	 * Allows the player to play the selected card and be affected by it.
	 * 
	 * @param player the {@link Player} that is playing
	 * @param cardName the card to be played (see {@link tda367.dominion.cardsTest})
	 */
	public void playCard(Player player, String cardName) {
		CardInfoHandler cih = CardInfoHandler.getInstance(); 
		if((player.hasCardInHand(cardName) &&(cih.getCardType(cardName).equals("Action") && player.getActions()>0) || cih.getCardType(cardName).equals("Treasure"))) {
			player.play(cardName);
			switch(cardName){
				case "Adventurer": Adventurer.play(game); break;
				case "Bureaucrat": activeCard = new Bureaucrat(game); activeCard.play(); break;
				case "Cellar": activeCard = new Cellar(game); activeCard.play(); break;
				case "Chancellor": activeCard = new Chancellor(game); activeCard.play(); break;
				case "Chapel": activeCard = new Chapel(game); activeCard.play(); break;
				case "Copper": player.increaseMoney(1); break;
				case "Councilroom": Councilroom.play(game); break;
				case "Feast": activeCard = new Feast(game); activeCard.play(); break;
				case "Festival": Festival.play(game); break;
				case "Gold": player.increaseMoney(3); break;
				case "Laboratory": Laboratory.play(game); break;
				case "Library": activeCard = new Library(game); activeCard.play(); break;
				case "Market": Market.play(player); break;
				case "Militia": activeCard = new Militia(game); activeCard.play(); break;
				case "Mine": activeCard = new Mine(game); activeCard.play(); break;
				case "Moat": Moat.play(game); break;
				case "Moneylender": Moneylender.play(player); break;
				case "Remodel": activeCard = new Remodel(game); activeCard.play(); break;
				case "Silver": player.increaseMoney(2); break;
				case "Smithy": Smithy.play(game); break;
				case "Spy": activeCard = new Spy(game); activeCard.play(); break;
//				case "Thief": Thief.play(player, players); break;
//				case "Throneroom": activeCard = new Throneroom(); activeCard.play(player); break;
				case "Village": Village.play(game); break;
				case "Witch": Witch.play(game); break;
				case "Woodcutter": Woodcutter.play(game); break;
				case "Workshop": activeCard = new Workshop(game); activeCard.play(); break;
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
	
	/**
	 * A method for checking if a card is active and waiting for input.
	 * @return
	 */
	public boolean isCardActive() {
		if (activeCard != null) {
			if (activeCard.isActive()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
