package tda367.dominion.server.game;

import java.util.LinkedList;
import java.util.List;

import tda367.dominion.commons.messages.*;
import tda367.dominion.server.network.GameConnection;

/**
 * The class that represents the player, holding the personal cards and the
 * values.
 * 
 * @author Group 28
 * 
 */
public class Player {
	private final GameConnection gameConnection;
	private final Pile hand;
	private final Pile deck;
	private final Pile discard;
	private final Pile playingArea;
	private final Pile revealedCards;
	private int actions;
	private int buys;
	private int money;
	private CardInfoHandler cih;

	/**
	 * Creates a new instance of Player with all the necessary piles (see
	 * {@link Pile}) and a name for easy identification.
	 * 
	 * <p>
	 * After giving all the necessary piles and an instance of the
	 * {@link CardInfoHandler} calls the init methods which gives the player the
	 * basic deck, shuffles it, draws five cards and sets actions and buys to an
	 * appropriate amount.
	 * 
	 * @param name
	 *            the name with which to identify the player
	 */
	public Player(GameConnection gc) {
		this.gameConnection = gc;

		hand = new Pile();
		deck = new Pile();
		discard = new Pile();
		playingArea = new Pile();
		revealedCards = new Pile();
		cih = CardInfoHandler.getInstance();

		this.actions = 0;
		this.buys = 0;
		this.money = 0;

		init();
	}

	/**
	 * Unborking for tests.
	 * 
	 * @param i
	 * @param string
	 */
	public Player(int i, String string) {
		this(new GameConnection());
	}

	private void init() {
		for (int i = 0; i < 7; i++) {
			deck.add("Copper");
		}
		for (int i = 0; i < 3; i++) {
			deck.add("Estate");
		}
		deck.shuffle();
		draw(5);
		this.actions = 1;
		this.buys = 1;
	}

	/**
	 * Draw card from deck to hand
	 * <p>
	 * Shuffles in the discard pile if the deck is empty
	 * </p>
	 */
	public void draw() {
		if (deck.getSize() == 0 && discard.getSize() > 0) {
			discardPileToDeck();
			hand.add(deck.pop());
		} else if (deck.getSize() > 0) {
			hand.add(deck.pop());
		}
	}

	private void discardPileToDeck() {
		deck.add(discard);
		deck.shuffle();
	}

	/**
	 * Draw multiple cards from deck to hand
	 * <p>
	 * Shuffles in the discard pile if the deck is empty
	 * </p>
	 */
	public void draw(int number) {
		for (int i = 0; i < number; i++) {
			draw();
		}
		this.updateCards();
		this.updateStats();
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
	 * Returns all playedCards in a pile
	 * 
	 * @Return playingArea
	 */
	public String[] getPlayedCards() {
		return this.playingArea.getCards().toArray(new String[0]);
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
	 * @return the name
	 */
	public String getName() {
		return gameConnection.getPlayerName();
	}

	/**
	 * @return the id
	 */
	public int getID() {
		return gameConnection.getID();
	}

	/**
	 * Discards every card in the players hand
	 */
	public void discardHand() {
		discard.add(hand);
		updateCards();
	}

	/**
	 * Discard specific card in the players hand
	 * 
	 * @param card
	 */
	public void discardCard(String card) {
		if (hand.contains(card)) {
			discard.add(hand.pop(card));
		}
		updateCards();
	}

	/**
	 * Add a card from supplies to the discard pile
	 * 
	 * @param card
	 */
	public void gain(String card) {
		discard.add(card);
		this.updateCards();
	}

	/**
	 * Add a card from supplies to the hand
	 * 
	 * @param card
	 */
	public void addToHand(String card) {
		hand.add(card);
	}

	/**
	 * Reveal the hand
	 * 
	 * @return the hand as a list
	 */
	public List<String> revealHand() {
		return hand.getCards();
	}

	/**
	 * Reveal the top card of the deck
	 * 
	 * @return the top card. NULL if there is no top card.
	 */
	public String revealTopOfDeck() {
		if (deck.getTop() == null) {
			discardPileToDeck();
		}
		return deck.getTop();
	}

	/**
	 * Reveal the top card of the deck
	 * 
	 * @return a list of the top cards
	 */
	public List<String> revealTopOfDeck(int number) {
		return deck.getTop(number);
	}

	/**
	 * Discard a card from the deck
	 * 
	 * @param card
	 *            in the deck to discard
	 */
	public void discardDeck() {
		discard.add(deck);
		updateCards();
	}

	/**
	 * Put a card on the top of the deck
	 * 
	 * @param card
	 *            to be put on the deck
	 */
	public void putOnTopOfDeck(String card) {
		deck.add(card);
	}

	/**
	 * Increases the number of actions by amount
	 * 
	 * @param the
	 *            number of actions added
	 */
	public void increaseActions(int amount) {
		this.actions += amount;
		updateStats();
	}

	/**
	 * Decreases the number of actions by amount
	 * 
	 * @param the
	 *            number of actions removed
	 */
	public void decreaseActions(int amount) {
		this.actions -= amount;
		updateStats();
	}

	/**
	 * Increases the amount of riksdaler available to the player by amount
	 * 
	 * @param the
	 *            number of riksdaler added
	 */
	public void increaseMoney(int amount) {
		this.money += amount;
		updateStats();
	}

	/**
	 * Decreases the amount of riksdaler available to the player by amount
	 * 
	 * @param the
	 *            number of riksdaler removed
	 */
	public void decreaseMoney(int amount) {
		this.money -= amount;
		updateStats();
	}

	/**
	 * Increases the number of buys by amount
	 * 
	 * @param the
	 *            number of buys added
	 */
	public void increaseBuy(int amount) {
		this.buys += amount;
		updateStats();
	}

	/**
	 * Decreases the number of buys by amount
	 * 
	 * @param the
	 *            number of buys removed
	 */
	public void decreaseBuy(int amount) {
		this.buys -= amount;
		updateStats();
	}

	/**
	 * An equals method
	 * 
	 * @param p
	 * @return
	 */
	public boolean equals(Player p) {
		if (p.getID() == this.getID()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Takes a specified card from hand and puts it in the playing area.
	 * 
	 * @param card
	 *            the card
	 */
	public void play(String card) {
		if (hand.contains(card)) {
			if (cih.isActionCard(card)) {
				actions--;
				playingArea.add(hand.pop(card));
			} else if (cih.isTreasureCard(card)) {
				playingArea.add(hand.pop(card));
				int inc = 0;

				if (cih.getCardValue(card) == 0) {
					inc = 1;
				} else if (cih.getCardValue(card) == 3) {
					inc = 2;
				} else if (cih.getCardValue(card) == 6) {
					inc = 3;
				}

				this.increaseMoney(inc);
			}
		}
		
	}

	/**
	 * Play the card in the specified position.
	 * 
	 * @param index
	 */
	public void play(int index) {
		if (hand.getSize() > 0) {
			String card = hand.getCard(index);

			if (cih.isActionCard(card)) {
				actions--;
			}

			if (!cih.isVictoryCard(card) && !cih.isCurseCard(card)) {
				playingArea.add(hand.pop(card));
			}
		}
		
	}

	/**
	 * Readies the player for a new turn, Empties the playing area into the
	 * discard pile, discards all cards in hand, draws 5 new and resets values.
	 */
	public void cleanUp() {
		discard.add(playingArea);
		discardHand();
		draw(5);
		this.actions = 1;
		this.money = 0;
		this.buys = 1;
		updateCards();
		updateStats();

	}

	/**
	 * Returns the number of cards in the players hand
	 * 
	 * @return number of cards in player hand
	 */
	public int getHandSize() {
		return this.hand.getSize();
	}

	/**
	 * A method used for testing
	 * 
	 * @return
	 */
	public Pile getDiscardPile() {
		return this.discard;
	}

	/**
	 * Returns all the cards in the players hand, Another method used for
	 * testing
	 * 
	 * @return The cards in the players hand
	 */
	public Pile getHand() {
		return this.hand;
	}

	/**
	 * Places the top card of the deck in the "revealed pile"
	 */
	public void setAsideTopOfDeck() {
		if (deck.getSize() > 0) {
			revealedCards.add(deck.pop());
		} else if (deck.getSize() == 0 && discard.getSize() > 0) {
			discardPileToDeck();
			revealedCards.add(deck.pop());
		}
	}

	/**
	 * Takes the revealed pile and adds it to discard.
	 */
	public void putRevealedCardsInDiscard() {
		discard.add(revealedCards);
	}

	/**
	 * Returns the revealed cards
	 * 
	 * @return the revealed cards
	 */
	public Pile getRevealedCards() {
		return this.revealedCards;
	}

	/**
	 * Trashes selected card from the hand
	 * 
	 * @param the
	 *            card to be trashed
	 * @return the trashed card
	 */
	public String trashCard(String card) {
		String trashedCard = hand.pop(card);
		updateCards();
		return trashedCard;

	}

	/**
	 * Trashes the selected card from the playing area. (Only used for feast)
	 * 
	 * @param card
	 *            the selected card.
	 */
	public void trashFromPlayingArea(String card) {
		if (playingArea.contains(card)) {
			playingArea.pop(card);
		}

	}

	public void discardTopOfDeck() {
		discard.add(deck.pop());
	}

	public String trashFromDeck(String cardName) {
		return deck.pop(cardName);
	}

	/**
	 * Plays all treasures from hand
	 */
	public void playAllTreasures() {
		for (String card : hand.getCards()) {
			if (cih.isTreasureCard(card)) {
				play(card);
			}
		}
	}

	public boolean hasActionCardsInHand() {
		for (String card : hand.getCards()) {
			if (cih.isActionCard(card)) {
				return true;
			}
		}
		return false;
	}

	public void send(Message msg) {
		gameConnection.sendTCP(msg);
	}

	public void updateCards() {
		CardUpdateMessage msg = new CardUpdateMessage();
		msg.setDeckSize(deck.getSize());
		msg.setDiscard(discard.getTop());
		msg.setHand(hand.getCards());
		msg.setInPlay(playingArea.getCards());
		send(msg);
	}

	public void updateStats() {
		PlayerUpdateMessage msg = new PlayerUpdateMessage();
		msg.setActions(actions);
		msg.setBuys(buys);
		msg.setMoney(money);
		send(msg);
	}

	public boolean hasActionCards() {
		if (hand != null) {
			for (int i = 0; i < hand.getSize(); i++) {
				if (cih.isActionCard(hand.getCard(i))) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void reveal(LinkedList<String> cards) {
		RevealMessage msg = new RevealMessage();
		for(String s : cards) {
			msg.addCard(s);
		}
		
		this.send(msg);
	}
	
	public void reveal(String card) {
		LinkedList<String> cards = new LinkedList<String>();
		cards.add(card);
		this.reveal(cards);
	}

	public void sendTip(String tip) {
		TipMessage tmsg = new TipMessage();
		tmsg.setMessage(tip);
		this.send(tmsg);
	}

	public void sendLog(String log) {
		LogMessage tmsg = new LogMessage();
		tmsg.setMessage(log);
		this.send(tmsg);
	}

	public boolean hasCardInHand(String card) {
		for (String s : hand.getCards()) {
			if (s.equals(card)) {
				return true;
			}
		}
		return false;
	}

}
