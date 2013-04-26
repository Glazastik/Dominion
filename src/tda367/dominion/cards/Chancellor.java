package tda367.dominion.cards;

import tda367.dominion.model.Player;

public class Chancellor implements ICard {

	public static void play(Player player) {
		player.increaseMoney(2);
		//player.createBoolMessage("Do you wish to discard your deck?")
		//Message temp = player.getNextMessage()
		//if(temp instanceOf YesNoMessage){
			//YesNoMessage tempMessage = (YesNoMessage) temp;
			//if(tempMessage.isTrue()){
				//player.discardDeck();
			//}
		//}
	}

	public static void discardDeck(Player player) {
		player.discardDeck();
	}

}
