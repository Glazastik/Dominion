package tda367.dominion.commons.messages;

/**
 * A message that tells the client the card that is revealed.
 */
public class RevealMessage {
	private String card;
	
	public void setCard(String card) {
		this.card = card;
	}
	
	public String getCard() {
		return card;
	}
}
