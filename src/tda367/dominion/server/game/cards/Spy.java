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
		System.out.println("Constructor done");
	}

	public void play() {
		state = State.ACTIVE;
		activePlayer = game.getActivePlayer();
		System.out.println("in play");
		int startingPos = players.indexOf(activePlayer);
		
		System.out.println(startingPos);
		System.out.println(players.size());
		
		for (int i = startingPos; i < players.size(); startingPos++) {
			orderedPlayers.add(players.get(i));
		}
		System.out.println("Exit first loop");
		for (int i = 0; i < startingPos; i++) {
			orderedPlayers.add(players.get(i));
		}
		System.out.println("Exit second loop");
		
		activePlayer.reveal(activePlayer.revealTopOfDeck());
		game.activateYesNoBox("Discard this from top of your deck?");

		for (Player p : orderedPlayers) {
			if (p.getHand().contains("Moat") && activePlayer != p) {
				notAffected.put(p, true);
			}
		}
		currentTarget = activePlayer;
		iterator = orderedPlayers.iterator();
	}

	@Override
	public void input(Message msg, Player p) {
		if (msg instanceof BoolMessage) {
			if (((BoolMessage) msg).isTrue()) {
				currentTarget.discardTopOfDeck();
			}
			while (iterator.hasNext()) {
				currentTarget = iterator.next();
				if (notAffected.get(currentTarget) && iterator.hasNext()) {
					// NÄSTA TARGET
				} else if (!notAffected.get(currentTarget)) {
					activePlayer.reveal(currentTarget.revealTopOfDeck());
					game.activateYesNoBox("Discard this from top of "
							+ p.getName() + "'s deck?");
					break;
				} else if (!iterator.hasNext()) {
					p.sendTip("Continue playing action cards.");
					state = State.NONACTIVE;
				}
			}

		}
		if (!iterator.hasNext()) {
			p.sendTip("Continue playing action cards.");
			state = State.NONACTIVE;
		}

	}
}
