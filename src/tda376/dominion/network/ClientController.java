package tda376.dominion.network;

import java.util.LinkedList;

import tda376.dominion.model.CardRulesHandler;
import tda376.dominion.model.Dominion;
import tda376.dominion.model.Player;

/**
 * A class that listens to datatraffic from the clients
 * and calls the correct method in Dominion. 
 * 
 * @author grupp 28
 */
public class ClientController {
	private Dominion dominion;
	private LinkedList<Player> players;
	private CardRulesHandler cardRulesHandler;
	public ClientController(Dominion model) {
		this.dominion = model;
		players = dominion.getPlayers();
		cardRulesHandler = dominion.getCardRulesHandler();
	}
//	public void parse(String message){
//		String[] splitString = message.split(" ");
//		//TODO: A whole bunch of work.
//		//Taking a string and translating it into method calls
//	}
}
