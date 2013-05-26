package tda367.dominion.server.game;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

import tda367.dominion.commons.messages.CreateBoolMessage;
import tda367.dominion.commons.messages.EndMessage;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.commons.messages.RevealMultipleCardMessage;
import tda367.dominion.commons.messages.SetupMessage;
import tda367.dominion.commons.messages.SupplyMessage;
import tda367.dominion.commons.messages.TurnMessage;
import tda367.dominion.server.game.TurnHandler.Phase;
import tda367.dominion.server.network.GameConnection;
import tda367.dominion.server.network.NetworkHandler;

/**
 * @author Group 28 Master class for one instance of a game.
 * 
 */
public class Dominion {
	// Player related
	private final LinkedList<Player> players;
	private TurnHandler turnHandler;
	private CardInfoHandler cih;
	private final Supply supply;
	private NetworkHandler network;
	private GainingHandler gainingHandler;

	/**
	 * Constructs a fine game of Dominion!
	 * 
	 * @param players
	 *            list of players playing the game
	 * @param supply
	 *            the supply depot for the piles
	 */
	public Dominion(LinkedList<Player> players) {
		this.players = players;
		this.supply = new Supply(players.size());
		turnHandler = new TurnHandler(players.size());
		gainingHandler = new GainingHandler(supply, turnHandler);
		network = NetworkHandler.getInstance();
		cih = CardInfoHandler.getInstance();
		init();
		startGame();
	}

	private void startGame() {

		turnHandler.startGame();
		this.notifyPhase();
		
		Set<String> ss = supply.getCardsInSupply().keySet();
		
		for(String s : ss) {
			System.out.println(s.toString());
		}
	}

	/**
	 * Play all the treasure cards the player has in hand.
	 * 
	 * @param gc
	 *            the requesting connection
	 */
	public void playAll(GameConnection gc) {
		if (this.getActiveID() != gc.getID()
				|| turnHandler.getPhase() != Phase.BUY) {
			return;
		}

		getActivePlayer().playAllTreasures();
		getActivePlayer().updateCards();
		getActivePlayer().updateStats();

	}

	public void done(GameConnection gc) {
		if (this.getActivePlayer().getID() == gc.getID()) {
			Phase next = turnHandler.advance();

			if (next == Phase.BUY) {
			} else if (next == Phase.CLEANUP) {

				this.getActivePlayer().cleanUp();
				turnHandler.advance();

				if (supply.gameIsOver()) {
					this.gameOver();
				}
			}

			notifyPhase();

		}
	}

	public void gameOver() {
		EndMessage msg = new EndMessage();

		LinkedList<String> names = new LinkedList<String>();

		for (Player p : players) {
			names.add(p.getName());
		}

		msg.setNames(names);
		msg.setScores(calculateScore());
		sendToAll(msg);
	}

	/**
	 * Calculates the score of the provided player.
	 * 
	 * @param player
	 *            the player whose score is to be calculated
	 * @return an int of calculated score
	 */
	private LinkedList<Integer> calculateScore() {
		LinkedList<Integer> scores = new LinkedList<Integer>();
		LinkedList<Player> players = getPlayers();

		for (Player p : players) {
			p.cleanUp();
			p.discardDeck();
			p.discardHand();
			scores.add(calculateScoreFromDeck(p.getDiscardPile().getCards()));
		}
		return scores;
	}

	/**
	 * Calculates the score for an individual player.
	 * 
	 * @param cards
	 *            the cards that the player has
	 * @return the players score
	 */
	private int calculateScoreFromDeck(LinkedList<String> cards) {
		int score = 0;

		for (String card : cards) {
			if (card.equals("Estate")) {
				score += 1;
			} else if (card.equals("Duchy")) {
				score += 3;
			} else if (card.equals("Province")) {
				score += 6;
			} else if (card.equals("Gardens")) {
				score += 1 * ((int) cards.size() % 10);
			} else if (card.equals("Curse")) {
				score -= 1;
			}
		}

		return score;
	}

	private void notifyPhase() {
		// TODO: Fix
		if (turnHandler.getPhase() == Phase.ACTION) {
			if (!this.getActivePlayer().hasActionCards()) {
				turnHandler.advance();
			}
		}
		sendTurnMessage(this.getActivePlayer(), turnHandler.getPhase());

	}

	private void sendTurnMessage(Player player, Phase phase) {
		TurnMessage msg = new TurnMessage();
		msg.setPhase(phase.toString());
		msg.setActive(this.getActivePlayer().getName());
		this.sendToAll(msg);
	}

	/**
	 * Sends a message that activates a yesNoBox in the view.
	 * 
	 * @param s
	 */
	public void activateYesNoBox(String s) {
		CreateBoolMessage cbm = new CreateBoolMessage();
		cbm.setText(s);
		sendToActive(cbm);
	}

	/**
	 * Sends a revealMultipleCardsMessage to everyone except the active player.
	 * 
	 * @param cards
	 */
	public void revealMultipleCards(String[] cards) {
		LinkedList<String> cardsLinked = new LinkedList<String>();
		for (String s : cards) {
			cardsLinked.add(s);
		}
		RevealMultipleCardMessage rmcm = new RevealMultipleCardMessage();
		rmcm.setCards(cards);
		for (Player p : getInactivePlayers()) {
			p.reveal(cardsLinked);
		}
		sendToAll(rmcm);
	}

	/**
	 * Sends a revealCardMessage to everyone except the active player.
	 * 
	 * @param cards
	 */
//	public void revealCard(String card) {
//		RevealCardMessage rcm = new RevealCardMessage();
//		rcm.setCard(card);
//		for (Player p : getInactivePlayers()) {
//			p.reveal(card);
//		}
//		sendToAll(rcm);
//	}

	public void updateSupply() {
		SupplyMessage msg = new SupplyMessage();
		msg.setSupply(supply.getCardsInSupply());
		this.sendToAll(msg);
	}

	/**
	 * Initialize the game, send messages to all involved players.
	 */
	private void init() {
		SetupMessage msg = new SetupMessage();
		SupplyMessage smsg = new SupplyMessage();

		smsg.setSupply(supply.getCardsInSupply());

		String[] s = new String[players.size()];

		for (int i = 0; i < players.size(); i++) {
			s[i] = players.get(i).getName();
		}

		msg.setPlayers(s);
		msg.setSupply(smsg);

		sendToAll(msg);

		for (Player p : players) {
			p.updateCards();
			p.updateStats();
		}
	}

	private int[] getPlayerIDs() {
		int[] ids = new int[players.size()];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = players.get(i).getID();
		}
		return ids;
	}

	public Player getActivePlayer() {
		int i = turnHandler.getActivePlayer();
		return players.get(i);
	}

	public int getActiveID() {
		int i = turnHandler.getActivePlayer();
		return players.get(i).getID();
	}

	/**
	 * Sends a particular message to all connected players.
	 * 
	 * @param msg
	 */
	private void sendToAll(Message msg) {
		for (Player p : players) {
			network.sendMessage(p.getID(), msg);
		}
	}

	/**
	 * Sends a particular message to all connected players.
	 * 
	 * @param msg
	 */
	private void sendToInactive(Message msg) {
		LinkedList<Player> inactive = getInactivePlayers();
		for (Player p : inactive) {
			network.sendMessage(p.getID(), msg);
		}
	}

	/**
	 * TODO: ADD DESCRIPTION
	 * 
	 * @param text
	 */
	public void sendLogToAll(String text) {
		for (Player p : players) {
			p.sendLog(text);
		}
	}

	/**
	 * Sends a particular message to the active player.
	 * 
	 * @param msg
	 */
	private void sendToActive(Message msg) {
		network.sendMessage(getActiveID(), msg);
	}

	public void playerBuyCard(String card) {
		gainingHandler.playerBuyCard(getActivePlayer(), card);
		SupplyMessage msg = new SupplyMessage();
		msg.setSupply(supply.getCardsInSupply());
		this.sendToAll(msg);
		this.sendLogToAll(this.getActivePlayer().getName() + " bought " + card);

	}

	public void playerBuyCard(Player p, String card) {
		gainingHandler.playerBuyCard(p, card);
	}

	/**
	 * Returns a list of players (see {@link Player}) that are playing the game.
	 * 
	 * @return a list containing the players
	 */
	public LinkedList<Player> getPlayers() {
		return this.players;
	}

	public LinkedList<Player> getInactivePlayers() {
		LinkedList<Player> l = new LinkedList<Player>();
		for (Player p : players) {
			if (!p.equals(this.getActivePlayer())) {
				l.add(p);
			}
		}
		return l;
	}

	/**
	 * Returns the Player having the specified connection
	 * 
	 * @param gc
	 *            the connection to check
	 * @return the player
	 */
	public Player getPlayer(GameConnection gc) {
		for (Player p : players) {
			if (p.getID() == gc.getID()) {
				return p;
			}
		}

		return null;
	}

	public Phase getPhase() {
		return turnHandler.getPhase();
	}

	/**
	 * Returns the {@link Supply} used in the game.
	 * 
	 * @return the {@link Supply}
	 */
	public Supply getSupply() {
		return this.supply;
	}

	/**
	 * Returns a HashMap of the current cards in supply & their amounts.
	 * 
	 * @return
	 */
	public HashMap<String, Integer> getSupplyInfo() {
		return supply.getCardsInSupply();

	}

	public void updateActive() {
		getActivePlayer().updateCards();
		getActivePlayer().updateStats();
	}

	public void checkDone(GameConnection gc) {
		Phase current = turnHandler.getPhase();
		Player active = this.getActivePlayer();
		if (current == Phase.ACTION && !active.hasActionCardsInHand()) {
			done(gc);
		} else if (current == Phase.BUY && active.getBuys() == 0) {
			done(gc);
		}
	}

}