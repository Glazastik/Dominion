package tda367.dominion.server.game.cards;

import java.util.HashMap;
import java.util.LinkedList;

import tda367.dominion.commons.messages.BoolMessage;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.Player;

public class Spy extends ChoiceCard {
	private LinkedList<Player> players;
	private HashMap<Player,Boolean> moatStatus;
	private Player activePlayer;
	private LinkedList<Player> orderedPlayers;
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
		//player.sendRevealedMessage(player.revealTopOfDeck());
		//player.sendTip("Discard this from top of " + p.getName() + "'s deck?");
		for(Player p: ordered){
			if(!p.getHand().contains("Moat") || player == p){
				//player.sendRevealMessage
				
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
				 * 			Spy(player, p);
				 * 		}
				 * 	}
				 * }
				 * p.removeInformationMessage();
				 * p.removeBoolMessage(); 
				 */
			}
		}
	}
	@Override
	public void input(Message msg, Player p) {
		if(msg instanceof BoolMessage){
			if(((BoolMessage)msg).isTrue()){
				
			}
		}
	}
}
