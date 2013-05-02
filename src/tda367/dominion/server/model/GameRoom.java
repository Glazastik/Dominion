package tda367.dominion.server.model;

import java.util.LinkedList;

import tda367.dominion.server.network.GameConnection;

/**
 * A game room, consists of four players playing a game of dominion.
 * 
 * @author Glazastik
 * 
 */
public class GameRoom {
	private LinkedList<Player> players;
	private LinkedList<GameConnection> gcs;
	private int slots;
	private String name;
	private int id;

	/**
	 * Initiates a Game Room
	 * 
	 * @param gc
	 */
	public GameRoom() {

		slots = 4;
		name = "test";
		id = 0;

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
		gcs.add(c);
		players.add(new Player(c.getPlayerName()));
		System.out.println("Added player to gameroom.");
		slots--;

	}

	public boolean isFull() {
		return slots <= 0;
	}
}
