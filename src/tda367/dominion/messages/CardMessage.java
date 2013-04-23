package tda367.dominion.messages;

/**
 * A message class used to represent cards clicked by the user Contains the card
 * name and the location in which it was clicked (hand, supply etc.)
 * 
 * @author christoffer
 * Cards using this message:
 * Bureaucrat
 * Cellar
 * Chapel
 * Feast
 * Library
 * Militia
 * Mine
 * Moneylender
 * Remodel
 * Spy
 * Thief
 * Throne room
 * Workshop
 */
public class CardMessage implements Message {
	private String cardName;
	private String location;

	public CardMessage(String cardName, String location) {
		this.cardName = cardName;
		this.location = location;
	}

	public String getCard() {
		return this.cardName;
	}

	public String getLocation() {
		return this.location;
	}

	public String toString() {
		return cardName + " " + location;
	}

	@Override
	public boolean equals(Object o) {
		//TODO decide if needed.
		
		if (o instanceof CardMessage) {
			CardMessage om = (CardMessage) o;
			if (om.getCard() == this.getCard()
					&& om.getLocation() == this.getLocation()) {
				return true;
			}
		}
		return false;
	}
}
