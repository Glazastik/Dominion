package tda376.dominion.model;

import java.awt.Image;

/**
 * 
 * The class that represents the cards.
 * @author Group 28
 *
 */
public class Card {
	//image type may change later 
	private final Image image;
	private final String name;
	
	public Card(String name, Image image) {
		this.name = name;
		this.image = image;
	}
	
	/**
	 * Getter for the name of the card
	 * @return The name of the card
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Getter for the image of the card
	 * @return The image of the card
	 */
	public Image getImage() {
		return this.image;
	}
}
