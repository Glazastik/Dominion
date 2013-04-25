package tda367.dominion.cards;

import tda367.dominion.model.Player;

public class Chancellor implements ICard {

	public static void play(Player player) {
		player.increaseMoney(2);
		//player.createBoolMessage("Do you wish to discard your deck?")
		//if(player.getNextMessage().instanceOf(YesNoMessage)){
			//if((YesNoMessage)player.getNextMessage().isTrue()){
				//player.discardDeck();
			//}
		//}
	}

	public static void discardDeck(Player player) {
		player.discardDeck();
	}

}
