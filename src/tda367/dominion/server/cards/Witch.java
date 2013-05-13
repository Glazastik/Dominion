package tda367.dominion.server.cards;

import java.util.LinkedList;

import tda367.dominion.server.game.GainingHandler;
import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.Supply;

public class Witch implements ICard {
	public static void play(Player player, LinkedList<Player> players, Supply supply){
		GainingHandler gH = new GainingHandler(supply);
		player.draw(2);
		for(Player p :players){
			if(p!=player){
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
}
