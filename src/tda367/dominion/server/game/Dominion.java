package tda367.dominion.server.game;

import java.util.HashMap;
import java.util.LinkedList;

import tda367.dominion.commons.messages.Message;
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

	private final Supply supply;
	private final CardRulesHandler cardRulesHandler;
	private NetworkHandler network;

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
		cardRulesHandler = new CardRulesHandler(players, supply);
		network = NetworkHandler.getInstance();

		init();
		startGame();
	}

	private void startGame() {
		turnHandler = new TurnHandler(players.size());
		turnHandler.startGame();
		this.notifyPhase();
	}

	public void playCard(GameConnection gc, String card) {
		if (this.getActiveID() != gc.getID()) {
			// TODO: Inte alltid retur pa den!
			return;
		} else {
			if (CardInfoHandler.isActionCard(card)) {
				if (getActivePlayer().getActions() > 0) {
					getActivePlayer().decreaseActions(1);
				} else {
					return;
				}
			}
			getActivePlayer().play(card);
		}
	}

	public void playBool(GameConnection gc, boolean bool) {
		if (turnHandler.getActivePlayer() == gc.getID()) {

		}
	}

	public void playGain(GameConnection gc, String card) {
		if (turnHandler.getActivePlayer() != gc.getID()) {
			return;
		}
		if (!supply.isAvailable(card)) {
			return;
		}

		for (Player p : players) {
			if (p.getID() == turnHandler.getActivePlayer()) {
				p.gain(card);
				return;
			}
		}
	}

	public void done(GameConnection gc) {
		if (this.getActivePlayer().getID() == gc.getID()) {
			Phase next = turnHandler.advance();

			if (next == Phase.BUY) {
				notifyPhase();
			} else if (next == Phase.CLEANUP) {
				this.getActivePlayer().cleanUp();
				turnHandler.advance();
				notifyPhase();
			}

		}
	}

	private void notifyPhase() {
		// TODO: Fix
		sendTurnMessage(this.getActivePlayer(), turnHandler.getPhase());
	}

	private void sendTurnMessage(Player player, Phase phase) {
		TurnMessage msg = new TurnMessage();
		msg.setPhase(phase.toString());
		msg.setActive(this.getActivePlayer().getName());
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

	private Player getActivePlayer() {
		int i = turnHandler.getActivePlayer();
		return players.get(i);
	}

	private int getActiveID() {
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
	 * Returns a list of players (see {@link Player}) that are playing the game.
	 * 
	 * @return a list containing the players
	 */
	public LinkedList<Player> getPlayers() {
		return this.players;
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
	 * Returns the {@link CardRulesHandler} used in the game.
	 * 
	 * @return the {@link CardRulesHandler}
	 */
	public CardRulesHandler getCardRulesHandler() {
		return this.cardRulesHandler;
	}

	/**
	 * Returns a HashMap of the current cards in supply & their amounts.
	 * 
	 * @return
	 */
	public HashMap<String, Integer> getSupplyInfo() {
		return supply.getCardsInSupply();

	}
}