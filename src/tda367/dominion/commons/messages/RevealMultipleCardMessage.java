package tda367.dominion.commons.messages;


/**
 * A class used to send a message cointaining multiple cards for exampel when a
 * player shows his hand to another player.
 * 
 * @author christoffer
 * 
 */
public class RevealMultipleCardMessage implements Message {
	private String[] cards;

	public void setCards(String[] cards) {
		this.cards = cards;
	}

	public String[] getCards() {
		return this.cards;
	}
}
