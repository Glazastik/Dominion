package tda367.dominion.server.game.cards;

import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.CardInfoHandler;
import tda367.dominion.server.game.GainingHandler;
import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.Supply;

public class Workshop implements ChoiceCard {
	public State state;
	private Supply supply;

	public Workshop(Supply s) {
		state = State.NONACTIVE;
		this.supply = s;
	}
	
	@Override
	public void play(Player p) {
		state = State.ACTIVE;
	}

	@Override
	public void input(Message msg, Player p) {
		if (msg instanceof CardMessage) {
			CardInfoHandler cih = CardInfoHandler.getInstance();
			if (cih.getCardValue(((CardMessage) msg).getCard()) < 5) {
				GainingHandler gh = new GainingHandler(supply);
				gh.playerGainCardToHand(p, ((CardMessage) msg).getCard());
				state = State.NONACTIVE;
			}
		}
		
	}
//	public static void play(Player p, Supply supply){
//		GainingHandler gH = new GainingHandler(supply);
//		boolean doneGaining = false;
//		//p.sendInformationMessage("Gain a card costing up to 4.");
//		while(!doneGaining){
//		//Message message = p.getNextMessage();
//		//if(message instanceOf LocatedCardMessage){
//			//LocatedCardMessage tempMessage = (LocatedCardMessage) message;
//			//if(tempMessage.getLocation().equals("Supply") && gh.isCardGainable(tempMessage.getCardName(), 4)){
//				//gH.playerGainCard(player, cardName);
//				//doneGaining = true;
//			//}
//		//}
//		}
//		//p.removeInformationMessage();
//	}
	
}
