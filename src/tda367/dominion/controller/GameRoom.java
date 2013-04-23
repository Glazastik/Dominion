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
	LinkedList<GameConnection> gcs;
	/**
	 * Initiates a Game Room
	 * @param gc
	 */
	public GameRoom (LinkedList<GameConnection> gcs){
		this.gcs = gcs;
		
		players = new LinkedList<Player>();
		for(GameConnection gc: gcs){
			players.add(new Player(gc.getPlayerName()));
		}
	}
	
	/**
	 * Returns a list of the player objects in the room.
	 * @return
	 */
	public LinkedList<Player> getPlayers(){
		return players;
	}
}
