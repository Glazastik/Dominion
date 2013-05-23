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
	private Dominion game;
	private HashMap<Player, Boolean> moatStatus;
	public Militia (LinkedList<Player> players){
		this.players = players;
		for(Player p : players){
			moatStatus.put(p, false);
		}
	}
	
	public Militia(Dominion game) {
		this.game = game;
	}

	public void play(){
		state = State.ACTIVE;
		activePlayer = game.getActivePlayer();
		game.getActivePlayer().increaseMoney(2);
		
		LinkedList<Player> players = game.getInactivePlayers();
		for(Player p: players){
			if(!p.getHand().contains("Moat")){
				//Militia(p);
				if(p.getHandSize()>3){
					p.sendTip("Discard down to 3 cards");
				}
			} else {
				moatStatus.put(p, true);
				/**
				 * p.sendInformationMessage("Do you wish to reveal Moat?");
				 * p.createBoolMessage();
				 * boolean done = false;
				 * while(!done){
				 * 	Message message = p.getNextMessage();
				 * 	if(message instanceOf BoolMessage){
				 * 		done = true;
				 * 		BoolMessage boolMessage = (BoolMessage) message;
				 * 		if(!boolMessage.isTrue()){
				 * 			Militia(p);
				 * 		}
				 * 	}
				 * }
				 * p.removeInformationMessage();
				 * p.removeBoolMessage(); 
				 */
				}
		}
		boolean noOneDiscards = true;
		for (Player p : players){
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
			}
		}
		if(noOneDiscards){
			state = State.NONACTIVE;
		}
	}

}
