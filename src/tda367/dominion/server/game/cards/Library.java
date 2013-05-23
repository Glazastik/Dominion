package tda367.dominion.server.game.cards;

import tda367.dominion.commons.messages.BoolMessage;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.CardInfoHandler;
import tda367.dominion.server.game.Player;

public class Library extends ChoiceCard{
	public Library(){
		state = State.NONACTIVE;
	}
	public void play(Player p){
		state = State.ACTIVE;
		CardInfoHandler cif = CardInfoHandler.getInstance();
		while(p.getHandSize() < 7){
			if(p.revealTopOfDeck() == null){
				state = State.NONACTIVE;
				break;
			} else if(cif.getCardType(p.revealTopOfDeck()).equals("Action")){
				//p.s
				//p.sendCardMessage(p.revealTopOfDeck());
				//p.createBoolMessage(),
				p.sendTip("Set aside: " + p.revealTopOfDeck() + "?");
				break;
			} else {
				p.draw();
			}
		}
		if(p.revealTopOfDeck() == null || p.getHandSize() >= 7){
			p.putRevealedCardsInDiscard();
			state = State.NONACTIVE;
		}
		//p.sendRemoveRevealedMessages();
	}
	@Override
	public void input(Message msg, Player p) {
		if(msg instanceof BoolMessage){
			if(((BoolMessage)msg).isTrue()){
				p.draw();
			} else {
				p.discardTopOfDeck();
			}
		}
		if(p.revealTopOfDeck() == null || p.getHandSize() >= 7){
			p.putRevealedCardsInDiscard();
			state = State.NONACTIVE;
		}
	}

}

