package tda367.dominion.server.game.cards;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import tda367.dominion.commons.messages.BoolMessage;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.Player;

public class Spy extends ChoiceCard {
	private LinkedList<Player> players;
	private HashMap<Player, Boolean> notAffected;
	private Player activePlayer;
	private LinkedList<Player> orderedPlayers;
	private Player currentTarget;
	private Iterator<Player> iterator;

	public Spy(Dominion game) {
		this.game = game;
		state = State.NONACTIVE;
		this.players = game.getPlayers();
		activePlayer = game.getActivePlayer();
		orderedPlayers = new LinkedList<Player>();
		notAffected = new HashMap<Player, Boolean>();
		for (Player p : players) {
			notAffected.put(p, false);
		}
	}

	public void play() {
		state = State.ACTIVE;
		activePlayer = game.getActivePlayer();
		
		int startingPos = players.indexOf(activePlayer);
		for (int i = startingPos; i < players.size(); i++) {
			orderedPlayers.add(players.get(i));
		}
		for (int i = 0; i < startingPos; i++) {
			orderedPlayers.add(players.get(i));
		}
		
		game.activateYesNoBox("Discard " + activePlayer.revealTopOfDeck() +" from top of your deck?");

		for (Player p : orderedPlayers) {
			if (p.getHand().contains("Moat") && activePlayer != p) {
				notAffected.put(p, true);
			}
		}
		iterator = orderedPlayers.iterator();
		currentTarget = iterator.next();
		
	}

	@Override
	public void input(Message msg, Player p) {
		System.out.println("MESSAGE");
		if (msg instanceof BoolMessage && p == activePlayer) {
			if (((BoolMessage) msg).isTrue()) {
				currentTarget.discardTopOfDeck();
				notAffected.put(currentTarget, true);
			}
			while (iterator.hasNext()) {
				currentTarget = iterator.next();
				if (notAffected.get(currentTarget) && iterator.hasNext()) {
					// N�STA TARGET
				} else if (!iterator.hasNext()) {
					p.sendTip("Continue playing action cards.");
					state = State.NONACTIVE;
					break;
				} else if (!notAffected.get(currentTarget)) {
					activePlayer.reveal(currentTarget.revealTopOfDeck());
					game.activateYesNoBox("Discard " + activePlayer.revealTopOfDeck() + " from top of " + p.getName() + "'s deck?");
					break;
				} 
			}

		}
		if (!iterator.hasNext()) {
			p.sendTip("Continue playing action cards.");
			state = State.NONACTIVE;
			activePlayer.draw();
			activePlayer.increaseActions(1);
		}

	}
}
