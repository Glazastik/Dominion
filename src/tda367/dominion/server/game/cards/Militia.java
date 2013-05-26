package tda367.dominion.server.game.cards;

import java.util.HashMap;
import java.util.LinkedList;

import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.Player;

public class Militia extends ChoiceCard {
	private LinkedList<Player> players;
	private Player activePlayer;
	private HashMap<Player, Boolean> notAffected;
	
	public Militia (Dominion game) {
		this.game = game;
		this.activePlayer = game.getActivePlayer();
		this.players = game.getPlayers();
		state = State.NONACTIVE;
		notAffected = new HashMap<Player, Boolean>();
		for(Player p : players){
			notAffected.put(p, false);
		}
	} 

	public void play(){
		state = State.ACTIVE;
		activePlayer = game.getActivePlayer();
		activePlayer.increaseMoney(2);
		LinkedList<Player> inactivePlayers = game.getInactivePlayers();
		
		for (Player p : inactivePlayers){
			System.out.println("Inactive: " + p.getName());
		}
		
		notAffected.put(activePlayer, true);
		for(Player p: inactivePlayers){
			if(!p.getHand().contains("Moat") && p.getHandSize()>3){
				System.out.println("PUTTING FALSE");
				System.out.println(p.getName());
				p.sendTip("Discard down to 3 cards");
				notAffected.put(p, false);
			} else {
				System.out.println("PUTTING TRUE");
				notAffected.put(p, true);
			}
		}
		
		boolean noOneDiscards = true;
		for (Player p : inactivePlayers){
			if(p.getHandSize()>3 && !notAffected.get(p)){
				noOneDiscards = false;
			}
		}
		if(noOneDiscards){
			state = State.NONACTIVE;
		}
	}
	@Override
	public void input(Message msg, Player player) {
		boolean doneDiscarding = false;
		
		if(!notAffected.get(player)){
			if(msg instanceof CardMessage && player.getHandSize()>3){
				if(player.hasCardInHand(((CardMessage) msg).getCard())){
					player.discardCard(((CardMessage) msg).getCard());
				}
			}
		}
		for (Player p : players){
			if(p == activePlayer || p.getHandSize() <= 3){
				notAffected.put(p, true);
			}
		}
		
		
		for (Player p : players){
			if(p != activePlayer && notAffected.get(p)){
				p.sendTip("Wait for " + activePlayer.getName() + " to finish playing action cards.");
			}
		}
		
		if(!notAffected.containsValue(false)){
			activePlayer.sendTip("Continue playing action cards.");
			state = State.NONACTIVE;
		}
	}

}
