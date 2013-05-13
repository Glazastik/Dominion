package tda367.dominion.server.game.cards;

import tda367.dominion.server.game.GainingHandler;
import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.Supply;

public class Workshop {
	public static void play(Player p, Supply supply){
		GainingHandler gH = new GainingHandler(supply);
		boolean doneGaining = false;
		//p.sendInformationMessage("Gain a card costing up to 4.");
		while(!doneGaining){
		//Message message = p.getNextMessage();
		//if(message instanceOf LocatedCardMessage){
			//LocatedCardMessage tempMessage = (LocatedCardMessage) message;
			//if(tempMessage.getLocation().equals("Supply") && gh.isCardGainable(tempMessage.getCardName(), 4)){
				//gH.playerGainCard(player, cardName);
				//doneGaining = true;
			//}
		//}
		}
		//p.removeInformationMessage();
	}
}
