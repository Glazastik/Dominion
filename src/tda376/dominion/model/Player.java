package tda376.dominion.model;

import java.util.List;

/**
 * The class that represents the player, holding the personal cards and the values.
 * @author Group 28
 *
 */
public class Player {
	private final String name;
	
	private final Pile hand;
	private final Pile deck;
	private final Pile discard;
	private final Pile playingArea;
	
	private int actions;
	private int buys;
	private int money;
	
	public Player(String name) {
		this.name = name;
		
		hand = new Pile();
		deck = new Pile();
		discard = new Pile();
		playingArea = new Pile();
		
		this.actions = 0;
		this.buys = 0;
		this.money = 0;
	}
	
	/**
	 * Draw card from deck to hand
	 * <p>Shuffles in the discard pile if the deck is empty</p>
	 */
	public void draw() {
		
	}
	
	/**
	 * Draw multiple cards from deck to hand
	 * <p>Shuffles in the discard pile if the deck is empty</p>
	 */
	public void draw(int number) {
		for(int i = 0; i < number; i++) {
			draw();
		}
	}
	
	/**
	 * Discards every card in the players hand
	 */
	public void discardHand() {
		int m = hand.getSize();
		for(int i = 0; i < m; i++ ) {
			discard.add(hand.pop());
		}
	}
	
	/**
	 * Discard specific card in the players hand
	 * @param card
	 */
	public void discardCard(Card card) {
		
	}
	
	/**
	 * Buy a card
	 * @param card
	 */
	public void buy(Card card) {
		
	}
	
	/**
	 * Add a card from supplies to the discard pile
	 * @param card
	 */
	public void gain(Card card) {
		discard.add(card);
	}
	
	/**
	 * Add a card from supplies to the hand
	 * @param card
	 */
	public void addToHand(Card card) {
		hand.add(card);
	}
	
	/**
	 * Reveal the hand
	 * @return the hand as a list
	 */
	public List<Card> revealHand() {
		return null;
	}
	
	/**
	 * Reveal the top card of the deck
	 * @return the top card
	 */
	public Card revealTopOfDeck() {
		return null;
	}
	
	/**
	 * Reveal the top card of the deck
	 * @return a list of the top cards
	 */
	public List<Card> revealTopOfDeck(int number) {
		return null;
	}
	
	/**
	 * Discard a card from the deck
	 * @param card in the deck to discard
	 */
	public void discardFromDeck(Card card) {
		
	}
	
	/**
	 * Put a card on the top of the deck
	 * @param card to be put on the deck
	 */
	public void putOnTopOfDeck(Card card) {
		
	}
	
}
