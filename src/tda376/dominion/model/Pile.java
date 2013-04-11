package tda376.dominion.model;

import java.util.ArrayList;

import com.sun.tools.javac.util.List;

/**
 * Class for the piles which contain the cards.
 * @author Group 28
 * 
 */

public class Pile {
	private final List<Card> cards;
	
	public Pile() {
		cards = new ArrayList<Card>();
	}
	
	public Pile(List<Card> cards){
		this.cards = cards;
		
	}
	
}
