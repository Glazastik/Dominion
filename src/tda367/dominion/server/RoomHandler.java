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
		String[][] roomString = new String[rooms.size()][];
		for(int i = 0; i < rooms.size(); i++){
			GameRoom gr = rooms.get(i);
			String[] tempRoom = new String[2];
			tempRoom[0] = gr.getName();
			tempRoom[1] = ""+gr.getSlots();
			roomString[i] = tempRoom;
		}
		
		return null;
	}
}
