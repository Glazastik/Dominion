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
	private final Pile revealedCards;
	private int actions;
	private int buys;
	private int money;
	private CardInfoHandler cardInfoHandler;
	
	public Player(String name) {
		this.name = name;
		
		hand = new Pile();
		deck = new Pile();
		discard = new Pile();
		playingArea = new Pile();
		revealedCards = new Pile();
		cardInfoHandler = CardInfoHandler.getInstance();
		
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
		if(deck.getSize() == 0 && discard.getSize() > 0) {
			discardPileToDeck();
			hand.add(deck.pop());
		} else if(deck.getSize() > 0) {
			hand.add(deck.pop());
		}
	}
	
	private void discardPileToDeck() {
		deck.add(discard);
		deck.shuffle();
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
	
	/**
	 * Returns the number of cards currently in the deck
	 * 
	 * @return number of cards
	 */
	public int getDeckSize() {
		return this.deck.getSize();
	}
	
	/**
	 * Discards every card in the players hand
	 */
	public void discardHand() {
		discard.add(hand);
	}
	
	/**
	 * Discard specific card in the players hand
	 * @param card
	 */
	public void discardCard(String card) {
		if(hand.contains(card)){
			discard.add(hand.pop(card));
		}
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
	 * @return the top card. NULL if there is no top card.
	 */
	public String revealTopOfDeck() {
		if(deck.getTop() == null){
			discardPileToDeck();
		}
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
		discard.add(deck.pop());
	}
	
	/**
	 * Put a card on the top of the deck
	 * @param card to be put on the deck
	 */
	public void putOnTopOfDeck(String card) {
		deck.add(card);
	}
	
	/**
	 * Increases the number of actions by amount
	 * @param the number of actions added 
	 */
	public void increaseActions(int amount) {
		this.actions += amount;
	}
	
	/**
	 * Decreases the number of actions by amount 
	 * @param the number of actions removed
	 */
	public void decreaseActions(int amount) {
		this.actions -= amount;
	}
	
	/**
	 * Increases the amount of riksdaler available to the player by amount
	 * @param the number of riksdaler added
	 */
	public void increaseMoney(int amount){
		this.money+=amount;
	}
	
	/**
	 * Decreases the amount of riksdaler available to the player by amount
	 * @param the number of riksdaler removed
	 */
	public void decreaseMoney(int amount){
		this.money-=amount;
	}
	
	/**
	 * Increases the number of buys by amount
	 * @param the number of buys added
	 */
	public void increaseBuy(int amount){
		this.buys+=amount;
	}
	
	/**
	 * Decreases the number of buys by amount
	 * @param the number of buys removed
	 */
	public void decreaseBuy(int amount){
		this.buys -= amount;
	}
	
	/**
	 * Resets the number of actions, buys and money available, after each turn.
	 */
	public void resetForNewTurn(){
		this.actions = 1;
		this.money = 0;
		this.buys = 1;
	}
	/**
	 * An equals method
	 * @param p
	 * @return
	 */
	public boolean Equals(Player p) {
		if(p.name == this.name) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Takes a specified card from hand and puts it in the playing area
	 * @param card the card
	 */
	public void play(String card){
		if(hand.contains(card)){
			if(cardInfoHandler.getCardType(card)== "Action"){
				actions--;
			}
			playingArea.add(hand.pop(card));
		}
	}
	/**
	 * Empties the playing area into the discard pile
	 */
	public void cleanUp(){
		discard.add(playingArea);
	}
	public int getHandSize(){
		return this.hand.getSize();
	}
	/**
	 * A method used for testing
	 * @return
	 */
	public Pile getDiscardPile(){
		return this.discard;
	}
	/**
	 * Places the top card of the deck in the "revealed pile"
	 */
	public void setAsideTopOfDeck(){
		if(deck.getSize()>0){
			revealedCards.add(deck.pop());
		} else if(deck.getSize()==0 && discard.getSize() > 0){
			deck.add(discard);
			deck.shuffle();
			revealedCards.add(deck.pop());
		}
	}
	/**
	 * Takes the revealed pile and adds it to discard.
	 */
	public void putRevealedCardsInDiscard(){
		discard.add(revealedCards);
	}
}
