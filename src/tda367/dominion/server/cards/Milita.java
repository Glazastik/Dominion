package tda367.dominion.cards;

import java.util.LinkedList;

import tda367.dominion.model.Player;

public class Milita {

	public static void play(Player player, LinkedList<Player> players){
		player.increaseMoney(2);
		for(Player p: players){
			if(player!=p){
				while(p.getHandSize()>3){
					//p.sendInformationMessage("Discard down to 3 cards");
					//Message temp = p.getNextMessage
					//if(temp instanceOf LocatedCardMessage){
						//LocatedCardMessage tempMessage = (LocatedCardMessage) temp;
						//if(p.getHand().contains(tempMessage.getCard()) && tempMessage.getLocation().equals("Hand")){
							//p.discardCard((LocatedCardMessage) temp.getCard)
						//}
					//}
					//p.removeInformationMessage();
				}
			}
		}
	}

}
