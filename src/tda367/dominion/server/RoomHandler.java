package tda367.dominion.server;

import java.util.LinkedList;

import tda367.dominion.controller.GameRoom;

/**
 * Knows about the states of the game rooms.
 * @author Glazastik
 *
 */
public class RoomHandler {
	LinkedList<GameRoom> rooms;
	
	public RoomHandler(){
		rooms = new LinkedList<GameRoom>();
	}
	
	public void createRoom(LinkedList<GameConnection> gcs){
		rooms.add(new GameRoom(gcs));
	}
	
	public String[][] getRoomsAsString(){
		return null;
	}
}
