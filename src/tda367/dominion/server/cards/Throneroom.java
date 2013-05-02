package tda367.dominion.server.cards;

import java.util.LinkedList;

import tda367.dominion.server.model.CardInfoHandler;
import tda367.dominion.server.model.Player;
import tda367.dominion.server.model.Supply;

public class Throneroom {
	public static void play(Player p, LinkedList<Player> players, Supply supply){
		CardInfoHandler cif = CardInfoHandler.getInstance();
		//p.sendInformationMessage("Choose an action card from your hand to play twice");
		boolean hasActionCards = false;
		boolean done = false;
		for(String s :p.getHand().getCards()){
			if(cif.getCardType(s).equals("Action")){
				hasActionCards = true;
			}
		}
		if(hasActionCards){
			while(!done){
		//Message message = p.getNextMessage();
		//if (message instanceOf LocatedCardMessage){
			//LocatedCardMessage lcm = (LocatedCardMessage) message;
			//String cardName = lcm.getCardName
			//if(lcm.getLocation().equals("Hand") && cif.getCardType(cardName).equals("Action"){
				//done=true;
				//p.removeInformationMessage();
				//player.increaseAction(1);
				//player.play(cardName);
				//for (int i = 0 ; i < 2 ; i++){
					//switch(cardName){
		//				case "Adventurer": Adventurer.play(); break;
		//				case "Cellar": Cellar.play(); break;
		//				case "Chapel": Chapel.play(); break;
		//				case "Councilroom": Councilroom.play(); break;
		//				case "Feast": Feast.play(); break;
		//				case "Festival": Festival.play(); break;
		//				case "Laboratory": Laboratory.play(); break;
		//				case "Library": Library.play(); break;
		//				case "Market": Market.play(); break;
		//				case "Militia": Militia.play(); break;
		//				case "Mine": Mine.play(); break;
		//				case "Moat": Moat.play(); break;
		//				case "Moneylender": Moneylender.play(); break;
		//				case "Remodel": Remodel.play(); break;
		//				case "Smithy": Smithy.play(); break;
		//				case "Spy": Spy.play(); break;
		//				case "Thief": Thief.play(); break;
		//				case "Throneroom": Throneroom.play(); break;
		//				case "Village": Village.play(); break;
		//				case "Witch": Witch.play(); break;
		//				case "Workshop": Workshop.play(); break;
						
						//
					//}
				//}
			//}
		//}
			}
		}
	}
}
