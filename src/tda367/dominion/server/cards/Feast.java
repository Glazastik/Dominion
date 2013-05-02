package tda367.dominion.server.cards;

import tda367.dominion.server.model.CardInfoHandler;
import tda367.dominion.server.model.GainingHandler;
import tda367.dominion.server.model.Player;
import tda367.dominion.server.model.Supply;

public class Feast {

	public static void play(Player p, Supply supply){
		boolean done = false;
		CardInfoHandler cardInfoHandler = CardInfoHandler.getInstance();
		p.trashFromPlayingArea("Feast");
		GainingHandler gH = new GainingHandler(supply);
		//p.sendInformationMessage("Chose a card to gain (costing 5 or less).
		while(!done){
			//Message temp = p.getNextMessage();
			//if(temp instanceOf LocatedCardMessage){
				//LocatedCardMessage tempMessage = (LocatedCardMessage) temp;
				//if(tempMessage.getLocation().equals("Supply" &&
					//gH.isCardGainable(tempMessage.getCardName(), 5)){
						//done = true;
						//gH.playerGainCard(player, tempMessage.getCardName())
				//}
			//}
		}
		//p.removeInformationMessage();
	}

}
