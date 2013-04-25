package tda367.dominion.controller;

import java.util.LinkedList;

import tda367.dominion.model.Player;
import tda367.dominion.server.GameConnection;

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

	/**
	 * Initiates a Game Room
	 * 
	 * @param gc
	 */
	public GameRoom(LinkedList<GameConnection> gcs) {
		this.gcs = gcs;
		slots = 0;
		name = "test";
		players = new LinkedList<Player>();
		for (GameConnection gc : gcs) {
			players.add(new Player(gc.getPlayerName()));
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
	 * @param slots the slots to set
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
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
