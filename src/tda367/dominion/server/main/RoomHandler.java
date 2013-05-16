package tda367.dominion.server.main;

import java.util.LinkedList;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import tda367.dominion.commons.messages.BoolMessage;
import tda367.dominion.commons.messages.DoneMessage;
import tda367.dominion.commons.messages.LocatedCardMessage;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.commons.messages.SetupMessage;
import tda367.dominion.commons.messages.SupplyMessage;
import tda367.dominion.server.game.Player;
import tda367.dominion.server.network.GameConnection;
import tda367.dominion.server.network.NetworkHandler;
import tda367.dominion.server.view.ServerFrame;

/**
 * Knows about the states of the game rooms.
 */
public class RoomHandler {
	LinkedList<GameRoom> rooms;
	private NetworkHandler network;
	private int id = -1;

	public RoomHandler() {
		rooms = new LinkedList<GameRoom>();
		network = NetworkHandler.getInstance();
		network.addListener(new NetworkListener());
	}

	/**
	 * Adds a GameRoom to the list.
	 * 
	 * @param name
	 */
	public void createRoom(String name) {
		if (rooms.size() < 10 && getRoomByName(name) == null) {
			rooms.add(new GameRoom(getNextID(), name));
		}
	}

	private int getNextID() {
		id++;
		return id;
	}

	/**
	 * Messages will use Strings for information.
	 * 
	 * @return
	 */
	public String[][] getRoomsAsString() {
		// TODO: Needs to be made prettier
		String[][] roomString = new String[rooms.size()][];
		for (int i = 0; i < rooms.size(); i++) {
			GameRoom gr = rooms.get(i);
			String[] tempRoom = new String[3];
			tempRoom[0] = "" + gr.getID();
			tempRoom[1] = gr.getName();
			tempRoom[2] = "" + gr.getSlots();
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
	public void addPlayer(GameConnection c, int id) {
		System.out.println("Trying to add player " + c.getPlayerName() + " to "
				+ id);
		GameRoom gr = this.getRoomById(id);
		if (gr.hasConnection(c)) {
			ServerFrame.getInstance().print(
					c.getPlayerName()
							+ " already has a connection to this game.");
		}

		gr.addPlayer(c);
	}

	/**
	 * Returns a room with corresponding ID.
	 * 
	 * @param id
	 *            the ID to search for.
	 * @return GameRoom or null if no room was found.
	 */
	public GameRoom getRoomById(int id) {
		for (GameRoom gr : rooms) {
			if (id == gr.getID()) {
				return gr;
			}
		}
		return null;
	}

	/**
	 * Returns a room with corresponding host's name.
	 * @param name 
	 * 
	 * @return GameRoom or null if no room was found.
	 */
	public GameRoom getRoomByName(String name) {
		for (GameRoom gr : rooms) {
			if (gr.getName().equals(name)) {
				return gr;
			}
		}
		return null;
	}
	
	/**
	 * Removes a player from the game
	 * 
	 * @param c
	 */
	public void kickConnection(GameConnection c) {
		for (GameRoom gr : rooms) {
			gr.kickPlayer(c.getPlayerName());
		}
	}

	public String[] getInfo(int id) {
		for (GameRoom gr : rooms) {
			if (gr.getID() == id) {
				String names = "";
				for (Player p : gr.getPlayers()) {
					names += p.getName();
				}
				return new String[] { "" + gr.getID(), gr.getName(),
						"" + gr.getSlots(), names };
			}
		}
		return null;
	}

	/**
	 * Returns all players connected to the specified room.
	 * 
	 * @param id
	 * @return
	 */
	public LinkedList<GameConnection> getPlayers(int id) {
		return this.getRoomById(id).getConnections();
	}

	/**
	 * Generates a SetupMessage containing players and supply.
	 * 
	 * @param id
	 * @return
	 */
	public SetupMessage getSetupMessage(int id) {
		SetupMessage msg = new SetupMessage();
		GameRoom gr = this.getRoomById(id);

		SupplyMessage smsg = new SupplyMessage();
		smsg.setSupply(gr.getModel().getSupplyInfo());

		msg.setSupply(smsg);
		msg.setPlayers(gr.getPlayerNames());

		return msg;
	}
	
	/**
	 * A class that listens for game specific requests. 
	 */
	class NetworkListener extends Listener {
		
		@Override
		public void received(Connection c, Object object) {
			
			if (object instanceof Message) {
				
			}
			
			if (object instanceof LocatedCardMessage) {
				
			}
			
			if (object instanceof BoolMessage) {
				
			}
			
			if (object instanceof DoneMessage) {
				
			}
		}
		
		@Override
		public void disconnected(Connection c) {
			kickConnection((GameConnection) c);
		}
	}

}
