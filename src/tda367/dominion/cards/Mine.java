package tda367.dominion.cards;

import tda367.dominion.model.CardInfoHandler;
import tda367.dominion.model.Player;

public class Mine {

	public static void play(Player p){
		CardInfoHandler cif = CardInfoHandler.getInstance();
		if(p.getHand().contains("Copper") || p.getHand().contains("Silver") || p.getHand().contains("Gold") ){
			//p.sendMessage("Trash a treasure from you hand.")
			//if(p.getNextMessage().instanceOf(LocatedCardMessage)){
			//LocatedCardMessage temp = (LocatedCardMessage) p.getNextMessage();
			//if(temp.getCardName.equals("Copper")){
			//
			//if(temp.getLocation().equals("Supply") && cif.getCardType(temp.getCardName).equals("Treasure") && cif.getCardValue(temp.getCardName)){
			
			
		}
	}
}
