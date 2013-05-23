package tda367.dominion.server.game.cards;

import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.Player;

public class Chancellor extends ChoiceCard {

	public void play(Dominion game) {
		game.getActivePlayer().increaseMoney(2);
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

	@Override
	public void input(Message msg, Player p) {
		// TODO Auto-generated method stub
	}
}
