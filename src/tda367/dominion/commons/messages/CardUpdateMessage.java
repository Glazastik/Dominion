package tda367.dominion.commons.messages;

import java.util.LinkedList;

/**
 * A message to send info about the cards
 */
public class CardUpdateMessage implements Message {
	private LinkedList<String> hand;
	private LinkedList<String> inPlay;
	private String discard;
	private int deckSize;
	
	public LinkedList<String> getHand() {
		return hand;
	}
	
	public void setHand(LinkedList<String> hand) {
		this.hand = hand;
	}
	
	public LinkedList<String> getInPlay() {
		return inPlay;
	}
	
	public void setInPlay(LinkedList<String> inPlay) {
		this.inPlay = inPlay;
	}
	
	public String getDiscard() {
		return discard;
	}
	
	public void setDiscard(String discard) {
		this.discard = discard;
	}
	
	public int getDeckSize() {
		return deckSize;
	}

	public void setDeckSize(int pileSize) {
		this.deckSize = pileSize;
	}
}
