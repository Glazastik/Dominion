package tda367.dominion.commons.messages;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * A class sent by the server to the client to initialize a game.
 * The data contained in the message should be enough for the client to start up the graphical view and such.
 * @author christoffer
 *
 */
public class InitializeMessege implements Message {
	//TODO: More data to add.
	private HashMap<String, Integer> cardsInSupply;
	private LinkedList<String> players;
	public InitializeMessege(HashMap<String,Integer> cardsInSupply, LinkedList<String> players) {
		this.cardsInSupply = cardsInSupply;
		this.players = players;
	}
	public HashMap<String,Integer> getCardsInSupply(){
		return cardsInSupply;
	}
	public LinkedList<String> getPlayers(){
		return players;
	}

}
