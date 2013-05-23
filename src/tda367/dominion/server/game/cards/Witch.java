package tda367.dominion.server.game.cards;

import java.util.LinkedList;

import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.GainingHandler;
import tda367.dominion.server.game.Player;

public class Witch {
	public static void play(Dominion game){
		GainingHandler gH = new GainingHandler(game.getSupply());
		game.getActivePlayer().draw(2);
		
		LinkedList<Player> players = game.getInactivePlayers();
		
		for(Player p :players){
			if(!p.getHand().contains("Moat")){
				gH.playerGainCard(p, "Curse");
			} else if(gH.isCardGainable("Curse")) {
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
				 * 			gH.playerGainCard(p, "Curse");
				 * 		}
				 * 	}
				 * }
				 * p.removeInformationMessage();
				 * p.removeBoolMessage(); 
				 */
			}
		}
	}
}
