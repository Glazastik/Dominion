package tda367.dominion.cards;

import tda367.dominion.model.Player;

public class Chancellor implements ICard {

	public static void play(Player player) {
		player.increaseMoney(2);
		//player.createInformationMessage("Do you wish to discard your deck?");
		//player.createBoolMessage();
		//Message temp = player.getNextMessage()
		//if(temp instanceOf BoolMessage){
			//BoolMessage tempMessage = (BoolMessage) temp;
			//if(tempMessage.isTrue()){
				//player.discardDeck();
			//}
		//}
		//player.removeBoolMessage();
		//player.removeInformationMessage();
	}
}
