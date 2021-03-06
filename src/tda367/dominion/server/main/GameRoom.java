package tda367.dominion.server.main;

import java.util.LinkedList;

import tda367.dominion.commons.game.CardInfoHandler;
import tda367.dominion.commons.messages.AdvanceMessage;
import tda367.dominion.commons.messages.BoolMessage;
import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.messages.DoneMessage;
import tda367.dominion.commons.messages.GainMessage;
import tda367.dominion.commons.messages.PlayAllMessage;
import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.TurnHandler.Phase;
import tda367.dominion.server.game.cards.Cellar;
import tda367.dominion.server.game.cards.Chapel;
import tda367.dominion.server.network.GameConnection;

/**
 * A game room, consists of four players playing a game of dominion.
 * 
 * @author Glazastik
 * 
 */
public class GameRoom {
	private static final int MAXPLAYERS = 2;
	private LinkedList<Player> players;
	private LinkedList<GameConnection> gcs;
	private CardRulesHandler cardRulesHandler;
	private CardInfoHandler cih;
	private int slots;
	private String name;
	private int id;

	private Dominion game;

	/**
	 * Initiates a Game Room
	 * 
	 * @param gc
	 */
	public GameRoom(int id, String gameName) {
		cih = CardInfoHandler.getInstance();
		slots = 0;
		name = gameName;
		this.id = id;

		if (gcs != null) {
			players = new LinkedList<Player>();
			for (GameConnection gc : gcs) {
				players.add(new Player(gc));
			}
		} else {
			gcs = new LinkedList<GameConnection>();
			players = new LinkedList<Player>();
		}
	}

	private void startGame() {
		game = new Dominion(this.getPlayers());
		cardRulesHandler = new CardRulesHandler(game);
	}

	public void received(GameConnection gc, Object object) {
		if (object instanceof CardMessage) {
			CardMessage message = ((CardMessage) object);
			if (cardRulesHandler.isCardActive()) {
				cardRulesHandler.activeCard.input(message,
						game.getPlayer(gc));
			} else {
				print("Player played: " + message.getCard());
				playCard(gc, message.getCard());
			}

		} else if (object instanceof BoolMessage) {
			if (cardRulesHandler.isCardActive()) {
				BoolMessage message = ((BoolMessage) object);
				cardRulesHandler.activeCard.input(message,
						game.getPlayer(gc));
			} else {
				BoolMessage message = ((BoolMessage) object);
				print("Bool: " + message.isTrue());
				playBool(gc, message.isTrue());
			}

		} else if (object instanceof GainMessage) {
			if (cardRulesHandler.isCardActive()) {
				GainMessage message = ((GainMessage) object);
				CardMessage cm = new CardMessage();
				cm.setCard(message.getCard());
				cardRulesHandler.activeCard.input(cm, game.getPlayer(gc));
				if (!cardRulesHandler.isCardActive()) {
					game.checkDone(gc);
				}
			} else {
				GainMessage message = ((GainMessage) object);
				print("Recieved gain message: " + message.getCard());
				playGain(gc, message.getCard());
			}
			game.updateSupply();

		} else if (object instanceof PlayAllMessage
				&& !cardRulesHandler.isCardActive()) {
			print("Received PlayAll");
			game.playAll(gc);
		} else if (object instanceof AdvanceMessage) {
			//Sends done message to chapel/cellar if they are active
			if (cardRulesHandler.activeCard instanceof Cellar
					|| cardRulesHandler.activeCard instanceof Chapel) {
				cardRulesHandler.activeCard.input(new DoneMessage(),
						game.getActivePlayer());
			} 
			//Next phase
			if (!cardRulesHandler.isCardActive()) {
				game.done(gc);
			}
		} else if (object instanceof DoneMessage) {
			if (cardRulesHandler.isCardActive()) {
				DoneMessage message = ((DoneMessage) object);
				cardRulesHandler.activeCard.input(message,
						game.getPlayer(gc));
				game.checkDone(gc);

			}
		} else {
			print("Classname: " + object.getClass());
			return;
		}
		game.updateActive();

	}

	public void playCard(GameConnection gc, String card) {
		if (game.getActiveID() != gc.getID()) {
			// TODO: Inte alltid retur pa den!
			return;
		} else {
			Phase phase = game.getPhase();
			if (cih.isActionCard(card) && phase == Phase.ACTION) {
				if (game.getActivePlayer().getActions() > 0) {
					cardRulesHandler.playCard(game.getActivePlayer(), card);
					game.sendLogToAll(game.getActivePlayer().getName()
							+ " played " + card);
					if (game.getActivePlayer().getActions() == 0
							&& !cardRulesHandler.isCardActive()) {
						game.done(gc);
					}
				} else {
					return;
				}
			} else if (cih.isTreasureCard(card) && phase == Phase.BUY) {
				game.getActivePlayer().play(card);
				game.updateActive();
			}
		}
	}

	public void playBool(GameConnection gc, boolean bool) {
		if (game.getActiveID() == gc.getID()) {

		}
	}

	public void playGain(GameConnection gc, String card) {
		if (game.getActiveID() == gc.getID() && game.getPhase() == Phase.BUY) {
			game.playerBuyCard(card);
			if (game.getActivePlayer().getBuys() == 0) {
				game.done(gc);
			}
		}

	}

	/**
	 * Returns a list of the player objects in the room.
	 * 
	 * @return
	 */
	public LinkedList<Player> getPlayers() {
		return players;
	}

	/**
	 * @return the slots
	 */
	public int getSlots() {
		return slots;
	}

	/**
	 * @param slots
	 *            the slots to set
	 */
	public void setSlots(int slots) {
		this.slots = slots;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}

	/**
	 * This is how we add a player to a game room. We need the current
	 * connection to be able to retrieve and send items.
	 * 
	 * @param c
	 *            the current connection of the player
	 */
	public void addPlayer(GameConnection c) {

		if (!hasPlayer(c.getPlayerName())) {
			gcs.add(c);
			players.add(new Player(c));
			System.out.println("Added " + c.getPlayerName() + " to gameroom.");
		} else {
			System.out.println("Player already existed...");
		}

		updateSlots();

		if (isFull() && !isPlaying()) {
			startGame();
		}
	}

	public boolean hasPlayer(String playerName) {
		for (Player p : players) {
			if (playerName.equals(p.getName())) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Checks if a connection exists within the game
	 * 
	 * @param c
	 *            the connection to be checked
	 * @return
	 */
	public boolean hasConnection(GameConnection c) {
		for (GameConnection gc : gcs) {
			if (gc.equals(c)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Adjusts the current number of available slots to the right number
	 */
	private void updateSlots() {
		slots = players.size();
	}

	/**
	 * If the room is full or not.
	 * 
	 * @return
	 */
	public boolean isFull() {
		return slots >= MAXPLAYERS;
	}

	/**
	 * Removes a connection and player object from the game. The Player-object
	 * is so far a risky remove.
	 * 
	 * @param playerName
	 */
	public void kickPlayer(String playerName) {
		for (Player p : players) {
			if (p.getName().equals(playerName)) {
				players.remove(p);
				break;
			}
		}

		for (GameConnection gc : gcs) {
			if (gc.getPlayerName().equals(playerName)) {
				gcs.remove(gc);
				break;
			}
		}
		updateSlots();
	}

	/**
	 * Checks if a game is on or not.
	 * 
	 * @return
	 */
	public boolean isPlaying() {
		return game != null;
	}

	/**
	 * Returns all players connected to this room
	 * 
	 * @return
	 */
	public LinkedList<GameConnection> getConnections() {
		return gcs;
	}

	public Dominion getModel() {
		return game;

	}

	public String[] getPlayerNames() {
		String[] names = new String[players.size()];
		for (int i = 0; i < players.size(); i++) {
			names[i] = players.get(i).getName();
		}
		return names;
	}

	private void print(String s) {
		System.out.println(s);
	}
}
