package tda367.dominion.commons.messages;

public class RevealCardMessage implements Message{
	private String cardName;

	public void setCard(String cardName) {
		this.cardName = cardName;
	}

	public String getCard() {
		return this.cardName;
	}

}
