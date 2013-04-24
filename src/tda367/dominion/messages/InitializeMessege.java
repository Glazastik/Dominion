package tda367.dominion.messages;

import java.util.HashMap;
import java.util.LinkedList;

public class InitializeMessege implements Message {
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
