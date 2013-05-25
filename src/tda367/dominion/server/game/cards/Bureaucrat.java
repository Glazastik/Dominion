package tda367.dominion.server.game.cards;

import java.util.HashMap;
import java.util.LinkedList;

import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.CardInfoHandler;
import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.GainingHandler;
import tda367.dominion.server.game.Player;

public class Bureaucrat extends ChoiceCard {
	private GainingHandler gH;
	private LinkedList<Player> inactivePlayers;
	private HashMap<Player,Boolean> notAffected;
	private CardInfoHandler cih;
	
	
	public Bureaucrat(Dominion dom){
		state = State.NONACTIVE;
		this.game = dom;
		this.gH = new GainingHandler(game.getSupply());
		cih = CardInfoHandler.getInstance();
	}
	public void play(){
		state = State.ACTIVE;
		this.inactivePlayers = game.getInactivePlayers();
		
		if(gH.isCardGainable("Silver")){
			game.getActivePlayer().putOnTopOfDeck(game.getSupply().take("Silver"));
		}
		for (Player p : game.getPlayers()){
			notAffected.put(p,(p.hasCardInHand("Moat")||p==game.getActivePlayer()));
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
		}
	}
	@Override
	public void input(Message msg, Player p) {
		if(msg instanceof CardMessage){
			if(!notAffected.get(p)){
				if(cih.isVictoryCard(((CardMessage)msg).getCard()) && p.hasCardInHand(((CardMessage)msg).getCard())){
					p.putOnTopOfDeck(p.getHand().pop(((CardMessage)msg).getCard()));
					notAffected.put(p, true);
					p.sendTip("Waiting for " +  game.getActivePlayer().getName() + " to finish their turn.");
				}
			}
		}
		if(!notAffected.containsValue(false)){
			game.getActivePlayer().sendTip("Continue");
			state = State.NONACTIVE;
		}
	}
}
