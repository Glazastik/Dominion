package tda367.dominion.cards;

import java.util.LinkedList;

import tda367.dominion.model.Player;

public class Milita {

	public static void play(Player player, LinkedList<Player> players){
		player.increaseMoney(2);
		for(Player p: players){
			if(player!=p){
				//TODO: Needs the client class on the server side for communication.
				while(p.getHandSize()>3){
					//p.sendMessage("Discard down to 3 cards");
					//if(p.getNextMessage().instanceOf(LocatedCardMessage)){
					//LocatedCardMessage temp = (LocatedCardMessage)p.getNextMessage();
						//p.discardCard(temp.getCard)
					//}
				}
			}
		}
	}

}
