package tda367.dominion.commons.messages;

import java.util.LinkedList;

/**
 * A message that tells the client the card that is revealed.
 */
public class RevealMessage implements Message{
	private LinkedList<String> cards = new LinkedList<String>();
	
	public void addCard(String card) {
		cards.add(card);
	}
	
	public LinkedList<String> getCards() {
		return cards;
	}
}
