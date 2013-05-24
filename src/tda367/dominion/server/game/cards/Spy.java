package tda367.dominion.server.game.cards;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import tda367.dominion.commons.messages.BoolMessage;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.Player;

public class Spy extends ChoiceCard {
	private LinkedList<Player> players;
	private HashMap<Player,Boolean> moatStatus;
	private Player activePlayer;
	private LinkedList<Player> orderedPlayers;
	private Player currentTarget;
	Iterator<Player> i;
	public Spy(LinkedList<Player> players){
		state = State.NONACTIVE;
		this.players = players;
		moatStatus = new HashMap<Player,Boolean>();
		for (Player p : players){
			moatStatus.put(p, false);
		}
	}
	public void play(Player player){
		state = State.ACTIVE;
		activePlayer = player;
		int startingPos = players.indexOf(player);
		for (int i = startingPos ; i < players.size() ; startingPos++){
			orderedPlayers.add(players.get(i));
		}
		for (int i = 0 ; i < startingPos ; i++){
			orderedPlayers.add(players.get(i));
		}
		player.s
		//player.sendRevealedMessage(player.revealTopOfDeck());
		player.sendTip("Discard this from top of " + player.getName() + "'s deck?");
		
		for(Player p: orderedPlayers){
			if(p.getHand().contains("Moat") && player != p){
				moatStatus.put(p, true);
			}
		}
		currentTarget = player;
		 i = orderedPlayers.iterator();
	}
	@Override
	public void input(Message msg, Player p) {
		if(msg instanceof BoolMessage){
			if(((BoolMessage)msg).isTrue()){
				currentTarget.discardTopOfDeck();
			}
			while(i.hasNext()){
				currentTarget = i.next();
				if(moatStatus.get(currentTarget) && i.hasNext()){
					//NÄSTA TARGET
				} else if (!moatStatus.get(currentTarget)) {
					p.sendTip("Discard this from top of " + currentTarget.getName() + "'s deck?");
					break;
				} else if (!i.hasNext()){
					state = State.NONACTIVE;
				}
			}
			
		}
		if(!i.hasNext()){
			state = State.NONACTIVE;
		}
		
	}
}
