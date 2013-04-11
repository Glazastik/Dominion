package tda376.dominion.model;

import java.util.LinkedList;

import java.util.List;

/**
 * Class for the piles which contain the cards.
 * @author Group 28
 * 
 */

public class Pile {
	private final List<Card> cards;
	
	public Pile() {
		cards = new LinkedList<Card>();
	}
	
	public Pile(List<Card> cards){
		this.cards = cards;
		
	}
	
}
