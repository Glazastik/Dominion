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
	private HashMap<Player, Boolean> moatStatus;
	
	public Militia (Dominion game) {
		this.game = game;
		this.activePlayer = game.getActivePlayer();
		this.players = game.getPlayers();
		state = State.NONACTIVE;
		moatStatus = new HashMap<Player, Boolean>();
		for(Player p : players){
			moatStatus.put(p, false);
		}
	} 

	public void play(){
		state = State.ACTIVE;
		activePlayer = game.getActivePlayer();
		activePlayer.increaseMoney(2);
		LinkedList<Player> inactivePlayers = game.getInactivePlayers();
		
		System.out.println("Active: " + activePlayer.getName());
		for (Player p : inactivePlayers){
			System.out.println("Inactive: " + p.getName());
		}
		
		moatStatus.put(activePlayer, true);
		for(Player p: inactivePlayers){
			if(!p.getHand().contains("Moat") && p.getHandSize()>3){
				System.out.println("PUTTING FALSE");
				System.out.println(p.getName());
				p.sendTip("Discard down to 3 cards");
				moatStatus.put(p, false);
			} else {
				System.out.println("PUTTING TRUE");
				moatStatus.put(p, true);
			}
		}
		
		boolean noOneDiscards = true;
		for (Player p : inactivePlayers){
			if(p.getHandSize()>3 && !moatStatus.get(p)){
				noOneDiscards = false;
			}
		}
		if(noOneDiscards){
			state = State.NONACTIVE;
		}
	}
	@Override
	public void input(Message msg, Player player) {
		System.out.println("Is in input");
		boolean doneDiscarding = false;
		System.out.println(player.getName());
		System.out.println(!moatStatus.get(player));
		System.out.println(player!=activePlayer);
		if(!moatStatus.get(player)){
			System.out.println("is not active player and no moatstatus");
			if(msg instanceof CardMessage && player.getHandSize()>3){
				if(player.hasCardInHand(((CardMessage) msg).getCard())){
					player.discardCard(((CardMessage) msg).getCard());
				}
			}
		}
		boolean noOneDiscards = true;
		for (Player p : players){
			if(p != player && p.getHandSize()>3 && !moatStatus.get(p)){
				noOneDiscards = false;
			} else {
				p.sendTip("Wait for " + activePlayer.getName() + " to finish playing action cards.");
			}
		}
		if(noOneDiscards){
			activePlayer.sendTip("Continue playing action cards.");
			state = State.NONACTIVE;
		}
	}

}
