package tda367.dominion.server.game.cards;

import java.util.HashMap;
import java.util.LinkedList;

import tda367.dominion.commons.game.CardInfoHandler;
import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.GainingHandler;
import tda367.dominion.server.game.Player;

public class Bureaucrat extends ChoiceCard {
	private GainingHandler gH;
	private LinkedList<Player> inactivePlayers;
	private HashMap<Player,Boolean> notAffected;
	private Player activePlayer;
	private CardInfoHandler cih;
	
	
	public Bureaucrat(Dominion dom){
		notAffected = new HashMap<Player,Boolean>();
		state = State.NONACTIVE;
		this.game = dom;
		this.gH = new GainingHandler(game.getSupply());
		cih = CardInfoHandler.getInstance();
		notAffected = new HashMap();
	}
	public void play(){
		state = State.ACTIVE;
		this.inactivePlayers = game.getInactivePlayers();
		this.activePlayer = game.getActivePlayer();
		
		if(gH.isCardGainable("Silver")){
			activePlayer.putOnTopOfDeck(game.getSupply().take("Silver"));
		}
		
		for (Player p : game.getPlayers()){
			if(p != activePlayer && !p.hasCardInHand("Moat") && (p.hasCardInHand("Estate") || p.hasCardInHand("Duchy") || p.hasCardInHand("Province") || p.hasCardInHand("Gardens"))){
				notAffected.put(p, false);
			} else {
				notAffected.put(p, true);
			}
		}
		
		for (Player p : inactivePlayers){
			if(!notAffected.get(p)){
				if(p.hasCardInHand("Estate") || p.hasCardInHand("Duchy") || p.hasCardInHand("Province") || p.hasCardInHand("Gardens")){
					p.sendTip("Choose a victory card to put on top of deck");
				}
			}
		}
		if(!notAffected.containsValue(false)){
			state = State.NONACTIVE;
			activePlayer.sendTip("Continue playing action cards.");
			for(Player p : inactivePlayers){
				p.sendTip("Wait for " + activePlayer.getName() + " to finish his/her turn.");
			}
		}
	}
	@Override
	public void input(Message msg, Player p) {
		if(msg instanceof CardMessage){
			if(!notAffected.get(p)){
				if(cih.isVictoryCard(((CardMessage)msg).getCard()) && p.hasCardInHand(((CardMessage)msg).getCard())){
					p.putOnTopOfDeck(p.getHand().pop(((CardMessage)msg).getCard()));
					p.updateCards();
					notAffected.put(p, true);
					p.sendTip("Waiting for " +  game.getActivePlayer().getName() + " to finish their turn.");
				}
			}
		}
		if(!notAffected.containsValue(false)){
			game.getActivePlayer().sendTip("Continue playing action cards");
			state = State.NONACTIVE;
		}
	}
}
