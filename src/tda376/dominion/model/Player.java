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
		
		init();
	}
	
	private void init() {
		for(int i = 0; i < 7; i++) {
			deck.add("Copper");
		}
		for(int i = 0; i < 3; i++) {
			deck.add("Estate");
		}
		deck.shuffle();
		draw(5);
		this.actions = 1;
		this.buys = 1;
	}
	
	/**
	 * Draw card from deck to hand
	 * <p>Shuffles in the discard pile if the deck is empty</p>
	 */
	public void draw() {
		if(deck.getSize() == 0) {
			discardPileToDeck();
		}
		hand.add(deck.pop());
	}
	
	private void discardPileToDeck() {
		
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
	 * Returns the amount of actions
	 * 
	 * @return actions
	 */
	public int getActions() {
		return this.actions;
	}
	
	/**
	 * Returns the amount of buys
	 * 
	 * @return buys
	 */
	public int getBuys() {
		return this.buys;
	}
	
	/**
	 * Returns the amount of money
	 * 
	 * @return money
	 */
	public int getMoney() {
		return this.money;
	}
	
	public int getDeckSize() {
		return this.deck.getSize();
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
	public void discardCard(String card) {
		
	}
	
	/**
	 * Add a card from supplies to the discard pile
	 * @param card
	 */
	public void gain(String card) {
		discard.add(card);
	}
	
	/**
	 * Add a card from supplies to the hand
	 * @param card
	 */
	public void addToHand(String card) {
		hand.add(card);
	}
	
	/**
	 * Reveal the hand
	 * @return the hand as a list
	 */
	public List<String> revealHand() {
		return hand.getCards();
	}
	
	/**
	 * Reveal the top card of the deck
	 * @return the top card
	 */
	public String revealTopOfDeck() {
		return deck.getTop();
	}
	
	/**
	 * Reveal the top card of the deck
	 * @return a list of the top cards
	 */
	public List<String> revealTopOfDeck(int number) {
		return deck.getTop(number);
	}
	
	/**
	 * Discard a card from the deck
	 * @param card in the deck to discard
	 */
	public void discardFromDeck(String card) {
		
	}
	
	/**
	 * Put a card on the top of the deck
	 * @param card to be put on the deck
	 */
	public void putOnTopOfDeck(String card) {
		
	}
	public void gainActions(int amount){
		this.actions += amount;
	}
	public void increaseMoney(int amount){
		this.money+=amount;
	}
	public void decreaseMoney(int amount){
		this.money-=amount;
	}
	public void gainBuy(int amount){
		this.buys+=amount;
	}
	public void resetForNewTurn(){
		this.actions = 1;
		this.money = 0;
		this.buys = 1;
	}
	
}
