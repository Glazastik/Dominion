package tda367.dominion.server.main;

import java.util.LinkedList;

import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.Player;
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

		slots = 0;
		name = gameName;
		this.id = id;

		if (gcs != null) {
			players = new LinkedList<Player>();
			for (GameConnection gc : gcs) {
				players.add(new Player(gc.getPlayerName()));
			}
		} else {
			gcs = new LinkedList<GameConnection>();
			players = new LinkedList<Player>();
		}
	}
	
	public void startGame(){
		game = new Dominion(this.getPlayers());
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
			players.add(new Player(c.getPlayerName()));
			System.out.println("Added " + c.getPlayerName() + " to gameroom.");
		} else {
			System.out.println("Player already existed...");
		}

		updateSlots();

	}

	public boolean hasPlayer(String playerName) {
		for (Player p : players) {
			if (playerName.equals(p.getName())){
				return true;
			}
		}
		return false;

	}

	/**
	 * Checks if a connection exists within the game
	 * @param c the connection to be checked
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
	 * @return
	 */
	public boolean isFull() {
		return slots >= MAXPLAYERS;
	}

	/**
	 * Removes a connection and player object from the game. 
	 * The Player-object is so far a risky remove.
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
	 * @return
	 */
	public boolean isPlaying() {
		return game != null;
	}

	/**
	 * Returns all players connected to this room
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
		for(int i = 0; i < players.size(); i++){
			names[i] = players.get(i).getName();
		}
		return names;
	}
}
