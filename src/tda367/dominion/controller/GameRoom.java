package tda367.dominion.controller;

import java.util.LinkedList;

import tda367.dominion.model.Player;
import tda367.dominion.server.GameConnection;

/**
 * A game room, consists of four players playing a game of dominion.
 * @author Glazastik
 *
 */
public class GameRoom {
	LinkedList<Player> players;
	/**
	 * Initiates a Game Room
	 * @param gc
	 */
	public GameRoom (LinkedList<GameConnection> gc){
		//TODO Add code
	}
	
	/**
	 * Returns a list of the player objects in the room.
	 * @return
	 */
	public LinkedList<Player> getPlayers(){
		return players;
	}
}
