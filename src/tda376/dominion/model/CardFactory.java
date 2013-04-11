package tda376.dominion.model;

/**
 * A factory used to create cards
 * @author christoffer
 *
 */
public interface CardFactory {
	/**
	 * A method for creating cards
	 * @param cardName the name of the card to be created
	 * @return the created card
	 */
	public ICard createCard(String cardName);
}
