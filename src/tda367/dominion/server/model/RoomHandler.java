package tda367.dominion.server.model;

import java.util.LinkedList;

import com.esotericsoftware.kryonet.Connection;

import tda367.dominion.server.network.GameConnection;

/**
 * Knows about the states of the game rooms.
 * 
 * @author Glazastik
 * 
 */
public class RoomHandler {
	LinkedList<GameRoom> rooms;

	public RoomHandler() {
		rooms = new LinkedList<GameRoom>();
	}

	/**
	 * Adds a GameRoom to the list.
	 * 
	 * @param gr
	 */
	public void createRoom(GameRoom gr) {
		rooms.add(gr);
	}

	/**
	 * Messages will use Strings for information.
	 * 
	 * @return
	 */
	public String[][] getRoomsAsString() {

		System.out.println("Rooms " + rooms.size());
		String[][] roomString = new String[rooms.size()][];
		for (int i = 0; i < rooms.size(); i++) {
			GameRoom gr = rooms.get(i);
			String[] tempRoom = new String[3];
			tempRoom[0] = gr.getName();
			tempRoom[1] = "" + gr.getSlots();
			tempRoom[2] = "" + gr.getID();
			roomString[i] = tempRoom;
		}

		return roomString;
	}

	/**
	 * Adds a player to the given room (id).
	 * 
	 * @param c
	 *            Connection c.
	 * @param id
	 *            The id of the room to add the player to.
	 */
	public boolean addPlayer(GameConnection c, int id) {
		System.out.println("Trying to add player " + c.getPlayerName() + " to "
				+ id);
		GameRoom gr = this.getRoomById(id);
		if (!gr.isFull() && !gr.hasConnection(c)) {
			gr.addPlayer(c);
			return true;
		}
		
		return false;
	}

	/**
	 * Returns a room with corresponding ID.
	 * 
	 * @param id
	 *            the ID to search for.
	 * @return GameRoom or null if no room was found.
	 */
	private GameRoom getRoomById(int id) {
		for (GameRoom gr : rooms) {
			int tempID = gr.getID();
			if (tempID == id) {
				return gr;
			}
		}
		return null;
	}

	public void kickConnection(GameConnection c) {
		
		for(GameRoom gr: rooms){
			gr.kickPlayer(c.getPlayerName());
			
		}
		
	}

	public String[] getInfo(int id) {
		for(GameRoom gr: rooms){
			if(gr.getID() == id){
				String names = "";
				for(Player p: gr.getPlayers()){
					names += p.getName();
				}
				return new String[] {""+gr.getID(), gr.getName(), ""+gr.getSlots(), names};
			}
			
		}
		return null;
		
	}
}
