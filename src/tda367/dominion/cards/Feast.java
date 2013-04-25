package tda367.dominion.cards;

import tda367.dominion.model.CardInfoHandler;
import tda367.dominion.model.GainingHandler;
import tda367.dominion.model.Player;
import tda367.dominion.model.Supply;

public class Feast {

	public static void play(Player p, GainingHandler gainingHandler, Supply supply){
		CardInfoHandler cardInfoHandler = CardInfoHandler.getInstance();
		p.trashFromPlayingArea("Feast");
		//p.sendMessage("Chose a card to gain (costing 5 or less).
		//if(p.getNextMessage().instanceOf(LocatedCardMessage)){
			//LocatedCardMessage temp = (LocatedCardMessage) p.getNextMessage();
			//if(temp.getLocation().equals("Supply"){
				//if(cardInfoHandler.getCardValue(temp.getCardName) <=5 && ){
//		supply.getGainableCards(5);
//		GainingHandler gainingHandler = new GainingHandler(supply);
//		gainingHandler.playerGainCard(player, cardName)
		
			//}	
		//}
	}

}
