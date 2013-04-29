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
public class LocatedCardMessage implements InstructionMessage {
	private String cardName;
	private String location;

	public String getLocation() {
		return this.location;
	}

	public String toString() {
		return cardName + " " + location;
	}
	
	

	/**
	 * @return the cardName
	 */
	public String getCardName() {
		return cardName;
	}

	/**
	 * @param cardName the cardName to set
	 */
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public boolean equals(Object o) {
		//TODO decide if needed.
		
		if (o instanceof LocatedCardMessage) {
			LocatedCardMessage om = (LocatedCardMessage) o;
			if (om.getCardName() == this.getCardName()
					&& om.getLocation() == this.getLocation()) {
				return true;
			}
		}
		return false;
	}
}
