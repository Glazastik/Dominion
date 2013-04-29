package tda367.dominion.server;

import com.esotericsoftware.kryonet.Connection;

/**
 * A connection a player has to the server.
 * @author Glazastik
 *
 */
public class GameConnection extends Connection{
	private String playerName;
	
	public GameConnection(){
		
	}
	
	public void setPlayerName(String name){
		this.playerName = name;
	}
	
	public String getPlayerName() {
		return null;
	}
	
	

}
