package tda367.dominion.server.model;

import java.util.LinkedList;

public class TurnHandler {
	private LinkedList<Player> players;
	private Player activePlayer;
	public TurnHandler(LinkedList<Player> players) {
		this.players = players;
		this.activePlayer = players.getFirst();
	}
	private void DoTurn(){
		boolean actionphaseDone = false;
		boolean buyphaseDone = false;
		//activePlayer.sendInformation
	}

}
