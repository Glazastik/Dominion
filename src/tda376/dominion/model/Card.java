package tda376.dominion.model;

import java.awt.Image;

/**
 * 
 * The class that represents the cards.
 * @author Group 28
 *
 */
public abstract class Card implements ICard {
	//private final Image image;
	private final String name;
	private final String cardType;
	private final int cardValue;
	//TODO: Should be created with an image and have a getImage method
	public Card(String name, String CardType) {
		this.name = name;
		this.cardType = CardType;
		//TODO: this.cardValue = 
		//this.image = image
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public String getCardType() {
		return cardType;
	}
	public int getCardValue(){
		return cardValue;
	}
}
