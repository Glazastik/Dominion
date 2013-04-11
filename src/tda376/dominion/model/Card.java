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
	
	public String getName() {
		return this.name;
	}
	
	public Image getImage() {
		return this.image;
	}
}
