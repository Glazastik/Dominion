package tda367.dominion.server.game.cards;

import java.util.HashMap;
import java.util.LinkedList;

import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.messages.InstructionMessage;
import tda367.dominion.commons.messages.InstructionMessageFactory;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.CardInfoHandler;
import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.GainingHandler;
import tda367.dominion.server.game.Pile;
import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.Supply;
import tda367.dominion.server.main.GameRoom;

public class Bureaucrat extends ChoiceCard {
	private Dominion dominion;
	private GainingHandler gH;
	private LinkedList<Player> inactivePlayers;
	private HashMap<Player,Boolean> notAffected;
	private CardInfoHandler cih;
	public Bureaucrat(Dominion dom){
		state = State.NONACTIVE;
		this.dominion = dom;
		this.gH = new GainingHandler(dominion.getSupply());
		cih = CardInfoHandler.getInstance();
	}
	public void play(Player player){
		state = State.ACTIVE;
		this.inactivePlayers = dominion.getInactivePlayers();
		
		if(gH.isCardGainable("Silver")){
			player.putOnTopOfDeck(dominion.getSupply().take("Silver"));
		}
		for (Player p : dominion.getPlayers()){
			notAffected.put(p,(p.hasCardInHand("Moat")||p==player));
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
				}
			}
		}
		if(!notAffected.containsValue(false)){
			state = State.NONACTIVE;
		}
	}
}
