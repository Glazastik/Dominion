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
	private HashMap<Player, Boolean> moatStatus;
	private Player activePlayer;
	private LinkedList<Player> orderedPlayers;
	private Player currentTarget;
	Iterator<Player> i;

	public Spy(Dominion dom) {
		game = dom;
		state = State.NONACTIVE;
		this.players = game.getPlayers();
		activePlayer = game.getActivePlayer();
		orderedPlayers = new LinkedList<Player>();
		moatStatus = new HashMap<Player, Boolean>();
		for (Player p : players) {
			moatStatus.put(p, false);
		}
	}

	public void play() {
		state = State.ACTIVE;
		int startingPos = players.indexOf(activePlayer);
		for (int i = startingPos; i < players.size(); startingPos++) {
			orderedPlayers.add(players.get(i));
		}
		for (int i = 0; i < startingPos; i++) {
			orderedPlayers.add(players.get(i));
		}
		game.revealCard(activePlayer.revealTopOfDeck());
		game.activateYesNoBox("Discard this from top of your deck?");

		for (Player p : orderedPlayers) {
			if (p.getHand().contains("Moat") && activePlayer != p) {
				moatStatus.put(p, true);
			}
		}
		currentTarget = activePlayer;
		i = orderedPlayers.iterator();
	}

	@Override
	public void input(Message msg, Player p) {
		if (msg instanceof BoolMessage) {
			if (((BoolMessage) msg).isTrue()) {
				currentTarget.discardTopOfDeck();
			}
			while (i.hasNext()) {
				currentTarget = i.next();
				if (moatStatus.get(currentTarget) && i.hasNext()) {
					// N�STA TARGET
				} else if (!moatStatus.get(currentTarget)) {
					game.revealCard(currentTarget.revealTopOfDeck());
					game.activateYesNoBox("Discard this from top of "
							+ p.getName() + "'s deck?");
					break;
				} else if (!i.hasNext()) {
					p.sendTip("Continue playing action cards.");
					state = State.NONACTIVE;
				}
			}

		}
		if (!i.hasNext()) {
			p.sendTip("Continue playing action cards.");
			state = State.NONACTIVE;
		}

	}
}
