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
		state = State.NONACTIVE;
		this.players = game.getPlayers();
		activePlayer = game.getActivePlayer();
		moatStatus = new HashMap<Player, Boolean>();
		for(Player p : players){
			moatStatus.put(p, false);
		}
	} 

	public void play(){
		state = State.ACTIVE;
		game.getActivePlayer().increaseMoney(2);
		
		LinkedList<Player> inActivePlayers = game.getInactivePlayers();
		for(Player p: inActivePlayers){
			if(!p.getHand().contains("Moat")){
				if(p.getHandSize()>3){
					p.sendTip("Discard down to 3 cards");
					moatStatus.put(p, false);
				}
			} else {
				moatStatus.put(p, true);
			}
		}
		boolean noOneDiscards = true;
		for (Player p : inActivePlayers){
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
		boolean doneDiscarding = false;
		if(player!=activePlayer && !moatStatus.get(player)){
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
