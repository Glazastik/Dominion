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
	
	public void createRoom(GameRoom gr){
		rooms.add(gr);
	}
	
	public String[][] getRoomsAsString(){
		
		System.out.println("Rooms " + rooms.size());
		String[][] roomString = new String[rooms.size()][];
		for(int i = 0; i < rooms.size(); i++){
			GameRoom gr = rooms.get(i);
			String[] tempRoom = new String[3];
			tempRoom[0] = gr.getName();
			tempRoom[1] = ""+gr.getSlots();
			tempRoom[2] = ""+gr.getID();
			roomString[i] = tempRoom;
		}
		
		return roomString;
	}

	public void addPlayer(GameConnection c, int id) {
		this.getRoomById(id).addPlayer(c);
	}
	
	private GameRoom getRoomById(int id){
		for(GameRoom gr: rooms){
			int tempID = Integer.parseInt(gr.getID());
			if(tempID == id){
				return gr;
			}
		}
		return null;
	}
}
