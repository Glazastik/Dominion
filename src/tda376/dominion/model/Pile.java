package tda376.dominion.model;

import java.util.Collections;
import java.util.LinkedList;

import java.util.List;

/**
 * Class for the piles which contain the cards.
 * @author Group 28
 * 
 */

public class Pile {
	private final LinkedList<String> cards;
	
	/**
	 * Creates an empty pile for holding cards
	 */
	public Pile() {
		cards = new LinkedList<String>();
	}
	
	/**
	 * Creates a pile containing the cards in the list provided
	 * 
	 * @param cards the cards that will populate the pile
	 */
	public Pile(LinkedList<String> cards) {
		this.cards = (LinkedList<String>) cards.clone();
	}
	
	/**
	 * Adds a card on the top of the pile
	 * 
	 * @param c the card that will be added to the top of the pile
	 */
	public void add(String c) {
		cards.addFirst(c);
	}
	
	/**
	 * Removes the top card from the pile and returns it
	 * 
	 * @return the top card of the pile
	 */
	public String pop() {
		return cards.pop();
	}
	
	/**
	 * Removes the first instance of the specific card
	 * 
	 * @param card
	 */
	public String pop(String card) {
		for(int i = 0; i < cards.size(); i++) {
			if(cards.get(i).equals(card)) {
				String temp = cards.get(i);
				cards.remove(i);
				return temp;
			}
		}
		return null;
	}
	
	/**
	 * Shuffles the pile, making the order of the cards random
	 */
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	/**
	 * Returns the size of the pile
	 * 
	 * @return the size of the pile
	 */
	public int getSize() {
		return cards.size();
	}
	
	/**
	 * Returns a list containing every card in the pile 
	 * 
	 * @return a list containing every card in the pile
	 */
	public LinkedList<String> getCards() {
		return (LinkedList<String>) cards.clone();
	}
	
	/**
	 * Reveal the top card in the Pile
	 * 
	 * @return card
	 */
	public String getTop() {
		return cards.getFirst();
	}
	
	/**
	 * Reveal a list of the top cards
	 * 
	 * @param number
	 * @return list with the n top cards
	 */
	public LinkedList<String> getTop(int number) {
		LinkedList l = new LinkedList<String>();
		int n = cards.size();
		if(n < number) {
			number = n;
		}
		for(int i = 0; i < n; i++) {
			l.add(cards.get(i));
		}
		return l;
	}
	
}
