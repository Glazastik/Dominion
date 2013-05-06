package tda367.dominion.commons.messages;

import java.util.ArrayList;

/**
 * A message to send info about the cards
 */
public class CardUpdateMessage {
	private ArrayList<String> hand;
	private ArrayList<String> inPlay;
	private String discard;
	private int deckSize;
	
	public ArrayList<String> getHand() {
		return hand;
	}
	
	public void setHand(ArrayList<String> hand) {
		this.hand = hand;
	}
	
	public ArrayList<String> getInPlay() {
		return inPlay;
	}
	
	public void setInPlay(ArrayList<String> inPlay) {
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
