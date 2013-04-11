package tda376.dominion.model;

public interface ICard {
	/**
	 * A method for getting the name of the card
	 * @return the name of the card
	 */
	public String getName();
	//TODO: public Image getImage();
	/**
	 * A method for getting the card type
	 * @return the type of the card
	 */
	public String getCardType();
	public int getCardValue();
}
