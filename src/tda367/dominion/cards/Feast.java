package tda367.dominion.cards;

import tda367.dominion.model.CardInfoHandler;
import tda367.dominion.model.GainingHandler;
import tda367.dominion.model.Player;
import tda367.dominion.model.Supply;

public class Feast {

	public static void play(Player p, Supply supply){
		boolean done = false;
		CardInfoHandler cardInfoHandler = CardInfoHandler.getInstance();
		p.trashFromPlayingArea("Feast");
		GainingHandler gH = new GainingHandler(supply);
		//p.sendMessage("Chose a card to gain (costing 5 or less).
		while(!done){
			//if(p.getNextMessage().instanceOf(LocatedCardMessage)){
				//LocatedCardMessage temp = (LocatedCardMessage) p.getNextMessage();
				//if(temp.getLocation().equals("Supply"){
					//if(gH.isCardGainable(temp.getCard(), 5)){
						//done = true;
						//gH.playerGainCard(player, temp.getCardName())
						
					//}	
				//}
			//}
		}
	}

}
