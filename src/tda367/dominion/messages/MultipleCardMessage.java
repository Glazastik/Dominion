package tda367.dominion.messages;

import java.util.LinkedList;
/**
 * A class used to send a message cointaining multiple cards for exampel when a player shows his hand to another player.
 * @author christoffer
 *
 */
public class MultipleCardMessage {
	private LinkedList<String> cards;
	public void setCards(LinkedList<String> cards){
		this.cards=cards;
	}
	public LinkedList<String> getCards(){
		return this.cards;
	}
}
