package tda367.dominion.client.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import tda367.dominion.client.model.Settings;
import tda367.dominion.commons.listener.GameEvent;
import tda367.dominion.commons.listener.GameListener;
import tda367.dominion.server.game.CardInfoHandler;
import tda367.dominion.server.game.Supply;

public class InGameState extends ControlledGameState {

	private CardInfoHandler cih;
	private HashMap<String, Integer> supply;

	private int[] nbrOfActionCards;
	private int[] nbrOfTreasureCards;
	private int[] nbrOfVictoryCards;

	/**
	 * Contains all the player's names. This includes the player using the
	 * client, not just opponents
	 */
	private String[] playerNames;

	/**
	 * Stores the amount of cards a player has on hand. cardsOnHand[0] refers to
	 * playerNames[0].
	 */
	private int[] cardsOnHand;

	/**
	 * Stores which players turn it is. turn == 1 means that it is
	 * playerNames[1]'s turn.
	 */
	private int turn;
	private Image[] actionCardsAll;
	private Image[] actionCards;
	private Image[] victoryCards;
	private Image[] treasureCards;
	private Image[] playedCards;
	private Image board = null;
	private int gameContainerWidth;
	private int gameContainerHeight;
	private int amountOfPlayers;
	private Image riksdaler = null;
	private Rectangle[] actionRectangles;
	private Rectangle[] victoryRectangles;
	private Rectangle[] treasureRectangles;
	private Rectangle[] handRectangles;
	private Image menuButton;
	private Image chatButton;
	private Image logButton;
	private Image nextButton;
	private Image playAllButton;
	private Image messageBox;
	private Image statusBar;
	private Image log;
	private Rectangle menuRec;
	private Rectangle chatRec;
	private Rectangle logRec;
	private Rectangle nextRec;
	private Rectangle playAllRec;

	// Log variables
	private boolean logDisplay;
	private LinkedList<String> logText;

	// Message to write in statusbar
	private String tipMessage;

	private boolean enterShowCard;// Temporary
	private Image cardToShow;// Temporary

	// Player stats
	private int actions;
	private int buys;
	private int money;

	// Cards
	private LinkedList<String> hand = new LinkedList<String>();
	private LinkedList<String> inPlay = new LinkedList<String>();
	private String topOfPile = "";
	private int deckSize = 0;

	// Listeners
	private GameListener cardListener;
	private GameListener supplyListener;
	private String phase;

	public InGameState(int id) {
		super(id);
	}

	public void addCardListener(GameListener l) {
		cardListener = l;
	}

	public void addSupplyListener(GameListener l) {
		supplyListener = l;
	}

	private void playCard(String card) {
		GameEvent e = new GameEvent(card);
		cardListener.run(e);
	}

	private void supplyCard(String card) {
		GameEvent e = new GameEvent(card);
		supplyListener.run(e);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		enterShowCard = false;
		cih = CardInfoHandler.getInstance();

		initPlayerInfo();
		initCards();

		String[] actions = cih.getActionCards().toArray(new String[0]);
		actionCardsAll = StringArraytoImageArray(actions);

		gameContainerWidth = gc.getWidth();
		gameContainerHeight = gc.getHeight();

		// Initiate all images
		initImages();

		// temporary status tip init
		tipMessage = "Juicy beatles";

		// Initiate all rectangles
		initRectangles();

		// Log init
		logDisplay = false;
		logText = new LinkedList<String>();

		for (int i = 0; i < 10; i++) {
			logText.addFirst("Test " + i);
		}
	}

	/**
	 * Initiates the player info.
	 */
	private void initPlayerInfo() {
		amountOfPlayers = 2; // Should probably be supplied from network later
		playerNames = new String[amountOfPlayers];
		cardsOnHand = new int[amountOfPlayers];

		playerNames[0] = Settings.getName();
		playerNames[1] = "Player 2";
		cardsOnHand[0] = 5; // Integer.parseInt(hand.get(0));
		cardsOnHand[1] = 5; // Integer.parseInt(hand.get(1));
		turn = 1;
	}

	/**
	 * Initiates the arrays holding cards.
	 */
	private void initCards() throws SlickException {
		treasureCards = StringArraytoImageArray(getTreasureCards(cih
				.getTreasureCards().toArray(new String[0])));
		victoryCards = StringArraytoImageArray(getVictoryCards(cih
				.getCardList().toArray(new String[0])));
	}

	/**
	 * Initiates the arrays holding the amount of cards.
	 */
	private void initAmounts() throws SlickException {
		nbrOfActionCards = getNbrOfCards(getActionCards(getSupply()));
		nbrOfTreasureCards = getNbrOfCards(getTreasureCards(getSupply()));
		nbrOfVictoryCards = getNbrOfCards(getVictoryCards(getSupply()));
	}

	/**
	 * Initiates every image that is used in the game.
	 * 
	 * @throws SlickException
	 */
	private void initImages() throws SlickException {
		menuButton = new Image("res/img/gui/ingame/MenuButton.png");
		chatButton = new Image("res/img/gui/ingame/ChatButton.png");
		logButton = new Image("res/img/gui/ingame/LogButton.png");
		riksdaler = new Image("res/img/gui/ingame/Coin.png");
		board = new Image("res/img/gui/ingame/BoardTemp.png");
		nextButton = new Image("res/img/gui/ingame/NextButton.png");
		playAllButton = new Image("res/img/gui/ingame/PlayAllTreasures.png");
		messageBox = new Image("res/img/gui/ingame/MessageBoxTemplate.png");
		statusBar = new Image("res/img/gui/ingame/StatusBar.png");
		log = new Image("res/img/gui/ingame/Log.png");
	}

	/**
	 * Initiates every rectangle field that is used in the game.
	 * 
	 */
	private void initRectangles() {
		actionRectangles = initRectangleArray(10);
		victoryRectangles = initRectangleArray(4);
		treasureRectangles = initRectangleArray(3);
		handRectangles = initRectangleArray(100);
	}

	/**
	 * Returns an array filled with normal rectangles.
	 * 
	 * <p>
	 * This is needed since the default value of the rectangles is null, so
	 * initializing a field is not enough, every rectangle has to be initialized
	 * as well.
	 * </p>
	 * 
	 * @param size
	 *            the size of the returned array
	 * @return an array filled with rectangles
	 */
	private Rectangle[] initRectangleArray(int size) {
		Rectangle[] recs = new Rectangle[size];

		for (int i = 0; i < size; i++) {
			recs[i] = new Rectangle();
		}

		return recs;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		board.draw();
		paintVictoryCards(victoryCards);
		paintActionCards(actionCards);
		paintTreasureCards(treasureCards);
		paintPlayerHand();
		paintNbrOfActionCards(nbrOfActionCards, actionCards, g);
		paintNbrOfTreasureCards(nbrOfTreasureCards, treasureCards, g);
		paintNbrOfVictoryCards(nbrOfVictoryCards, victoryCards, g);
		paintOpposingPlayers(g);
		paintButtons();
		paintStatusBar(g);
		paintLog(g);
		paintPhase(g);

		if (playedCards != null) {
			paintPlayedCards(playedCards);
		}

		// paintMessageBox("Test Baws" ,g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int time)
			throws SlickException {

		Input input = gc.getInput();
		gameContainerWidth = gc.getWidth();
		gameContainerHeight = gc.getHeight();

		if (inPlay.size() > 0) {
			playedCards = StringListToImageArray(inPlay);
		} else {
			playedCards = null;
		}

		if (enterShowCard) {
			enterShowCard = false;
			String temp = cardToShow.getResourceReference();
			cardToShow = new Image(temp.split("Supply")[0] + ".jpg");
			((ShowCardState) sbg.getState(Settings.SHOWCARDSTATE))
					.showCard(cardToShow);
			sbg.enterState(Settings.SHOWCARDSTATE);
		}

		// Return to menu
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			sbg.enterState(1);
		}

	}

	

	/**
	 * Updates the arrays containing the amount of cards that are in play.
	 * 
	 * <p>
	 * This needs to be called EVERY time a card is purchased from the supply.
	 * If not, the informations displayed will be faulty and no one will be
	 * happy.
	 * </p>
	 */
	public void updateNbrOfCards() {
		nbrOfActionCards = getNbrOfCards(getActionCards(getSupply()));
		nbrOfTreasureCards = getNbrOfCards(getTreasureCards(getSupply()));
		nbrOfVictoryCards = getNbrOfCards(getVictoryCards(getSupply()));
	}

	/**
	 * Sets the amount of actions that are to be displayed.
	 * 
	 * <p>
	 * This does not affect the player in any way, it only affects the number
	 * that is displayed. It is therefore crucial that this the same as the
	 * amount of actions the player has access to, otherwise information
	 * displayed will be faulty.
	 * </p>
	 * 
	 * @param actions
	 *            an int specifying the amount of actions a player has
	 */
	public void setActions(int actions) {
		this.actions = actions;
	}

	/**
	 * Sets the amount of buys that are to be displayed.
	 * 
	 * <p>
	 * This does not affect the player in any way, it only affects the number
	 * that is displayed. It is therefore crucial that this the same as the
	 * amount of buys the player has access to, otherwise information displayed
	 * will be faulty.
	 * </p>
	 * 
	 * @param buys
	 *            an int specifying the amount of buys a player has
	 */
	public void setBuys(int buys) {
		this.buys = buys;
	}

	/**
	 * Sets the amount of money that are to be displayed.
	 * 
	 * <p>
	 * This does not affect the player in any way, it only affects the number
	 * that is displayed. It is therefore crucial that this the same as the
	 * amount of money the player has access to, otherwise information displayed
	 * will be faulty.
	 * </p>
	 * 
	 * @param money
	 *            an int specifying the amount of money a player has
	 */
	public void setMoney(int money) {
		this.money = money;
	}

	/**
	 * Sets the player's hand and resets the rectangles which acts as listeners.
	 * 
	 * <p>
	 * This method should be called every time the hand is modified in any way,
	 * such as drawing cards or playing one.
	 * </p>
	 * 
	 * @param hand2
	 *            an {@link ArrayList} containing the cards in the players hand
	 */
	public void setHand(LinkedList<String> hand2) {
		this.hand = hand2;
		resetHandRectangles();
	}

	/**
	 * Sets what games are currently in play.
	 * 
	 * @param inPlay2
	 *            an {@link ArrayList} containing the cards that have been
	 *            played
	 */
	public void setInPlay(LinkedList<String> inPlay2) {
		this.inPlay = inPlay2;
	}

	/**
	 * Sets the card that is on top of the discard pile.
	 * 
	 * <p>
	 * This is relevant since that card should always be shown. This in turn
	 * means that this method should be called every time a card is put in the
	 * discard pile, such as when discarding or gaining.
	 * </p>
	 * 
	 * @param topOfPile
	 *            the card that is on top of the pile, represented in String
	 *            format
	 */
	public void setTopOfPile(String topOfPile) {
		this.topOfPile = topOfPile;
	}

	/**
	 * Sets the size of the players deck.
	 * 
	 * @param size
	 *            and int specifying the deck's size
	 */
	public void setDeckSize(int size) {
		deckSize = size;
	}
	
	/**
	 * Sets the message to be shown in the middle of the status bar.
	 * 
	 * @param message, the message to be shown.
	 */
	public void setTipMessage(String message) {
		tipMessage = message;
	}

	/**
	 * Adds a string to the log, so it appears at the bottom. Adds it to the
	 * first position because the paint method draws the first text first.
	 * 
	 * @param str
	 */
	public void addLogMessage(String str) {
		logText.addFirst(str);
	}

	/**
	 * Takes care of all actions that calls mouseClicked
	 */
	@Override
	public void mouseClicked(int button, int x, int y, int clicks) {
		String card;

		// Victory cards listener
		card = recCheck(button, x, y, victoryCards, victoryRectangles);
		if (card != null) {
			System.out.println(card);
			return;
		}

		// Treasure cards listener
		card = recCheck(button, x, y, treasureCards, treasureRectangles);
		if (card != null) {
			System.out.println(card);
			return;
		}

		// Action cards listener
		card = recCheck(button, x, y, actionCards, actionRectangles);
		if (card != null) {
			System.out.println(card);
			return;
		}

		// Hand cards listener
		try {
			card = recCheck(button, x, y,
					StringArraytoImageArray(hand.toArray(new String[0])),
					handRectangles);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		if (card != null) {
			System.out.println(card);
			playCard(card);
			return;
		}

		// Menu button listener
		if (button == Input.MOUSE_LEFT_BUTTON && menuRec.contains(x, y)) {
			System.out.println("Menu button");
		}

		// Chat button listener
		if (button == Input.MOUSE_LEFT_BUTTON && chatRec.contains(x, y)) {
			System.out.println("Chat Button");
		}

		// Log button listener
		if (button == Input.MOUSE_LEFT_BUTTON && logRec.contains(x, y)) {
			System.out.println("Log Button");
			logDisplay = !logDisplay;
		}

		// Next/End button listener
		if (button == Input.MOUSE_LEFT_BUTTON && nextRec.contains(x, y)) {
			hand.add("Copper");
			try {
				if (nextButton.getResourceReference() == "res/img/gui/ingame/NextButton.png") {
					nextButton = new Image(
							"res/img/gui/ingame/EndTurnButton.png");
				} else {
					// TODO: Replace with network stuff
					// player.cleanUp();
					nextButton = new Image("res/img/gui/ingame/NextButton.png");
				}
			} catch (SlickException s) {

			}
		}

		// play all treasures button listener
		if (button == Input.MOUSE_LEFT_BUTTON && playAllRec.contains(x, y)) {
			System.out.println("Play all treasures");
			hand.removeLast();
			// TODO: Replace with network stuff
			// crh.playAllTreasures(player);
		}
	}

	/**
	 * This method is called every time this state is left.
	 * 
	 * <p>
	 * Its current behavior is that it clears the record of keys pressed,
	 * removing any unwanted behavior in states switched to.
	 * <p/>
	 * 
	 * @param gc
	 *            the {@link GameContainer} this state is contained in
	 * @param sbg
	 *            the {@link StateBasedGame} this state is a part of
	 */
	@Override
	public void leave(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		gc.getInput().clearKeyPressedRecord();
	}

	/**
	 * Iterates over an array of rectangles and checks if they have been clicked
	 * by user.
	 * 
	 * <p>
	 * If a rectangle has been clicked, the name of the underlying card is
	 * returned.
	 * </p>
	 * 
	 * @param button
	 *            the button that was clicked
	 * @param x
	 *            x-position of mouse cursor
	 * @param y
	 *            y-position of mouse cursor
	 * @param cards
	 *            an array of card images
	 * @param recs
	 *            an array of rectangles
	 * 
	 * @return the name of the clicked card, or null if no card was clicked
	 */
	private String recCheck(int button, int x, int y, Image[] cards,
			Rectangle[] recs) {
		for (int i = 0; i < recs.length; i++) {
			if (recContainsLeftClick(recs[i], button, x, y)) {
				return splitString(cards[i]);
			} else if (recContainsRightClick(recs[i], button, x, y)) {// Checking
																		// for
																		// detailed
																		// view
				setDetailed(cards[i]);
				return null;
			}
		}

		return null;
	}

	/**
	 * Splits a cards image reference to get its name.
	 * 
	 * @param cards
	 *            the card whose resource is to be split
	 * 
	 * @return the name of the card derived from the image resource
	 */
	private String splitString(Image card) {
		String temp = card.getResourceReference().split("card/")[1];
		temp = temp.split("Supply.jpg")[0];
		temp = temp.split(".jpg")[0];
		return temp;
	}

	/**
	 * Sets what card to show in the detailed view, and tells the state to enter
	 * it.
	 * 
	 * @param cardToShow
	 *            the card to show in the detailed views
	 */
	private void setDetailed(Image cardToShow) {
		enterShowCard = true;
		this.cardToShow = cardToShow;
	}

	/**
	 * Checks if a rectangle has been clicked by the left mouse button.
	 * 
	 * @param rec
	 *            the rectangle to be checked
	 * @param button
	 *            the clicked button
	 * @param x
	 *            x-position of the cursor
	 * @param y
	 *            y-position of the cursor0
	 * @return true if the rectangle was clicked
	 */
	private boolean recContainsLeftClick(Rectangle rec, int button, int x, int y) {
		return button == Input.MOUSE_LEFT_BUTTON && rec.contains(x, y);
	}

	/**
	 * Checks if a rectangle has been clicked by the right mouse button.
	 * 
	 * @param rec
	 *            the rectangle to be checked
	 * @param button
	 *            the clicked button
	 * @param x
	 *            x-position of the cursor
	 * @param y
	 *            y-position of the cursor
	 * @return true if the rectangle was clicked
	 */
	private boolean recContainsRightClick(Rectangle rec, int button, int x,
			int y) {
		return button == Input.MOUSE_RIGHT_BUTTON && rec.contains(x, y);
	}

	/**
	 * Returns all the cardnames in the supply.
	 * 
	 * <p>
	 * In future updates, this should probably also do something sensible with
	 * the amount of cards left in the supply. But we'll get to that when we
	 * have to.
	 * </p>
	 * 
	 * @return every cardname in the supply
	 */
	String[] getSupply() {
		return supply.keySet().toArray(new String[0]);
	}

	/**
	 * Returns all the actioncards from an array of cardnames.
	 * 
	 * <p>
	 * Although this method promises to return all actioncards, it will also
	 * return a copy of gardens if one is present in the string of cardnames.
	 * This is because a copy of gardens should be displayed with the
	 * actioncards in the supply, and the true purpose of this method is the get
	 * the ten cards that should be displayed in the supply.
	 * </p>
	 * 
	 * <p>
	 * Worthy of mention is that the cards are retured sorted in descending
	 * order, i.e. the most expensive first.
	 * </p>
	 * 
	 * @param cards
	 *            the array of cardnames that will be searched for actioncards
	 * @return an array containing every actioncard found
	 */
	private String[] getActionCards(String[] cards) {
		cih = CardInfoHandler.getInstance();
		List<String> allActionCards = cih.getActionCards();
		String[] cardsToReturn = new String[10];

		int index = 0;
		for (int i = 0; i < cards.length; i++) {
			if (allActionCards.contains(cards[i])) {
				cardsToReturn[index] = cards[i];
				index++;
			}
		}

		for (int i = 0; i < cards.length; i++) {
			if (cards[i].equals("Gardens")) {// Ensures Gardens DOES end up with
												// the actioncards
				cardsToReturn[index] = cards[i];
			}
		}

		return sortStringArray(cardsToReturn);
	}

	/**
	 * Returns all the victorycards from an array of cardnames
	 * 
	 * <p>
	 * Although it says victorycards, this method will also fetch curse cards,
	 * as they are somewhat related, at least when they will be drawn out in the
	 * supply are.
	 * </p>
	 * 
	 * <p>
	 * Worthy of mention is that the cards are retured sorted in descending
	 * order, i.e. the most expensive first.
	 * </p>
	 * 
	 * @param cards
	 *            the array of cardnames that will be searched for victorycards
	 * @return an array containing every victorycard found
	 */
	private String[] getVictoryCards(String[] cards) {
		cih = CardInfoHandler.getInstance();
		List<String> allVictoryCards = cih.getVictoryCards();
		String[] cardsToReturn = new String[4];

		int index = 0;
		for (int i = 0; i < cards.length; i++) {
			if (allVictoryCards.contains(cards[i])) {
				if (!cards[i].equals("Gardens")) {// Ensures Gardens does NOT
													// end up with the
													// victorycards
					cardsToReturn[index] = cards[i];
					index++;
				}
			}
		}

		for (int i = 0; i < cards.length; i++) {
			if (cards[i].equals("Curse")) {// Ensures that Curse DOES end up
											// with the victorycards
				cardsToReturn[index] = cards[i];
			}
		}

		return sortStringArray(cardsToReturn);
	}

	/**
	 * Returns all the treasurecards from an array of cardnames.
	 * 
	 * <p>
	 * The cards are returned in descending order, i.e. highest cost first.
	 * </p>
	 * 
	 * @param cards
	 *            the array of cardnames that will be searched for treasurecards
	 * @return an array containg every treasurecard found
	 */
	private String[] getTreasureCards(String[] cards) {
		cih = CardInfoHandler.getInstance();
		List<String> allTreasureCards = cih.getTreasureCards();
		String[] cardsToReturn = new String[3];

		int index = 0;
		for (int i = 0; i < cards.length; i++) {
			if (allTreasureCards.contains(cards[i])) {
				cardsToReturn[index] = cards[i];
				index++;
			}
		}

		return sortStringArray(cardsToReturn);
	}

	/**
	 * This method takes an array of cardnames in the form of strings and
	 * returns an array with the path to the image of these cards.
	 * 
	 * <p>
	 * Note that this method is only useable for string arrays with cardnames,
	 * and nothing else. While you can send in any kind of String array,
	 * anything but the recommended will not result in anything but a garbled
	 * heap of crap.
	 * </p>
	 * 
	 * @param cards
	 * @return
	 */
	private Image[] StringArraytoImageArray(String[] cards)
			throws SlickException {
		Image[] imageArray = new Image[cards.length];
		cih = CardInfoHandler.getInstance();

		for (int i = 0; i < cards.length; i++) {
			imageArray[i] = new Image(cih.getCroppedImageLink(cards[i]));
		}

		return imageArray;
	}

	/**
	 * This method takes an array of cardnames in the form of strings and
	 * returns an array with the path to the image of these cards.
	 * 
	 * <p>
	 * Note that this method is only useable for string arrays with cardnames,
	 * and nothing else. While you can send in any kind of String array,
	 * anything but the recommended will not result in anything but a garbled
	 * heap of crap.
	 * </p>
	 * 
	 * @param inPlay2
	 * @return
	 */
	private Image[] StringListToImageArray(LinkedList<String> inPlay2)
			throws SlickException {
		Image[] imageArray = new Image[inPlay2.size()];
		cih = CardInfoHandler.getInstance();

		for (int i = 0; i < inPlay2.size(); i++) {
			imageArray[i] = new Image(cih.getCroppedImageLink(inPlay2.get(i)));
		}

		return imageArray;
	}

	/**
	 * Returns a sorted clone of the given String array containing cardnames.
	 * 
	 * <p>
	 * This method is only good for String arrays containing cardnames, much
	 * like {@link StringArrayToImageArray}. This method sorts in desscending
	 * order based in cost, i.e. the most expensive card is the first element.
	 * </p>
	 * 
	 * @param cards
	 *            an array of strings containing cardnames
	 * @return a clone of the given array in sorted order.
	 */
	private String[] sortStringArray(String[] cards) {
		cih = CardInfoHandler.getInstance();
		String[] sortedArray = cards.clone();
		for (int threshold = sortedArray.length - 1; threshold > 0; threshold--) {
			for (int i = 0; i < threshold; i++) {
				if (cih.getCardValue(sortedArray[(i + 1)]) > cih
						.getCardValue(sortedArray[i])) {
					String temp = sortedArray[i];
					sortedArray[i] = sortedArray[(i + 1)];
					sortedArray[(i + 1)] = temp;
				}
			}
		}

		return sortedArray;
	}

	/**
	 * Paints the victorycards in appropriate places. Also sets the location of
	 * victoryRectangles to the same places.
	 * 
	 * <p>
	 * Although any sort of image array may be passed to this method, it is
	 * tailored for images of dominion cards, specifically those of the victory
	 * category, and nothing else.
	 * </p>
	 * 
	 * <p>
	 * Also note that victorycards in this sense includes the Curse-card, but
	 * excludes Gardens, and that the size of the array should be 4. If it is
	 * not, this method cannot be guaranteed to work properly.
	 * </p>
	 * 
	 * @param cards
	 *            the images to be painted
	 */
	private void paintVictoryCards(Image[] cards) {
		float cardHeight;
		double scale;
		float cardWidth;
		int xOffset = 5;
		int yOffset = 5;
		int cardSpacing = 5;

		for (int i = 0; i < cards.length; i++) {
			cardWidth = (float) gameContainerWidth / 14;
			scale = (double) cardWidth / cards[i].getWidth();
			cardHeight = (float) (cards[i].getHeight() * scale);

			cards[i].draw(xOffset,
					cardHeight * i + (i * cardSpacing) + yOffset, cardWidth,
					cardHeight);
			victoryRectangles[i].setLocation(xOffset, (int) cardHeight * i
					+ (i * cardSpacing) + yOffset);
			victoryRectangles[i].setSize((int) cardWidth, (int) cardHeight);
		}
	}

	/**
	 * Paints the actioncards in appropriate places. Also places
	 * actionRectangles in the same places.
	 * 
	 * <p>
	 * Although any sort of image array may be passed to this method, it is
	 * tailored for images of dominion cards, specifically those of the action
	 * category, and nothing else.
	 * </p>
	 * 
	 * <p>
	 * Also note that action cards in this sense includes Gardens. The size of
	 * the array should be 10. If it is not, this method cannot be guaranteed to
	 * work properly.
	 * </p>
	 * 
	 * @param cards
	 *            the images to be painted
	 */
	private void paintActionCards(Image[] cards) {
		float cardHeight;
		double scale;
		float cardWidth;
		int xOffset = 15 + gameContainerWidth / 7;
		int cardSpacing = 5;
		int yOffset = 5;

		for (int i = 0; i < cards.length; i++) {
			cardWidth = (float) (gameContainerWidth / 9);
			scale = (double) cardWidth / cards[i].getWidth();
			cardHeight = (float) (cards[i].getHeight() * scale);

			if (i < cards.length / 2) {
				cards[i].draw(xOffset + cardWidth * (i) + (i * cardSpacing),
						yOffset, cardWidth, cardHeight);
				actionRectangles[i].setLocation((int) (xOffset + cardWidth
						* (i) + (i * cardSpacing)), yOffset);
				actionRectangles[i].setSize((int) cardWidth,
						(int) cardHeight + 5);
			} else {
				cards[i].draw(xOffset + cardWidth * (i - 5)
						+ ((i - 5) * cardSpacing), cardHeight + 2 * yOffset,
						cardWidth, cardHeight);
				actionRectangles[i].setLocation((int) (xOffset + cardWidth
						* (i - 5) + (i * cardSpacing)), (int) cardHeight + 2
						* yOffset);
				actionRectangles[i].setSize((int) cardWidth, (int) cardHeight);
			}
		}
	}

	/**
	 * Paints the treasurecards in appropriate places. Also places
	 * treasureRectangles in the same places.
	 * 
	 * <p>
	 * Although any sort of image array may be passed to this method, it is
	 * tailored for images of dominion cards, specifically those of the action
	 * category, and nothing else.
	 * </p>
	 * 
	 * <p>
	 * Also note that the size of the array should be 3. If it is not, this
	 * method cannot be guaranteed to work properly.
	 * </p>
	 * 
	 * @param cards
	 */
	private void paintTreasureCards(Image[] cards) {
		float cardHeight;
		double scale;
		float cardWidth;
		int yOffset = 5;
		int cardSpacing = 5;

		for (int i = 0; i < cards.length; i++) {
			cardWidth = (float) gameContainerWidth / 14;
			scale = (double) cardWidth / cards[i].getWidth();
			cardHeight = (float) (cards[i].getHeight() * scale);
			int xOffset = (int) (cardWidth + 10);

			cards[i].draw(xOffset,
					yOffset + cardHeight * i + (i * cardSpacing), cardWidth,
					cardHeight);
			treasureRectangles[i].setLocation((int) (xOffset), (int) cardHeight
					* i + (i * cardSpacing) + yOffset);
			treasureRectangles[i].setSize((int) cardWidth, (int) cardHeight);
		}
	}
	
	private void paintPhase(Graphics g) {
		
	}

	/**
	 * Paints the cards in the players hand.
	 * 
	 * <p>
	 * Although any sort of image array may be passed to thus method, it is
	 * tailored for images of dominion cards, and giving it anything else may
	 * result in unwanted behavior.
	 * </p>
	 * 
	 * @param cards
	 */
	private void paintPlayerHand() throws SlickException {
		cih = CardInfoHandler.getInstance();
		String[] stringCards = hand.toArray(new String[0]);
		Image[] imageCards = new Image[stringCards.length];

		for (int i = 0; i < hand.size(); i++) {
			imageCards[i] = new Image(cih.getImageLink(stringCards[i]));
		}

		for (int i = 0; i < imageCards.length; i++) {
			float cardHeight = (float) gameContainerHeight * (float) (1.0 / 3);
			double scale = (double) cardHeight / imageCards[i].getHeight();
			float cardWidth = (float) (imageCards[i].getWidth() * scale);
			float cardOverlap;
			if (imageCards.length > 6) {
				cardOverlap = (float) imageCards.length / (float) 5.5;
			} else {
				cardOverlap = 1;
			}

			int xOffset = (int) (5 + cardWidth / cardOverlap * i);
			int yOffset = (int) (gameContainerHeight - cardHeight - 10);

			imageCards[i].draw(xOffset, yOffset, cardWidth, cardHeight);
			if (i < imageCards.length - 1) {
				handRectangles[i].setBounds(xOffset, yOffset,
						(int) (cardWidth / cardOverlap), (int) cardHeight);
			} else {
				handRectangles[i].setBounds(xOffset, yOffset, (int) cardWidth,
						(int) cardHeight);
			}
		}

	}

	/**
	 * Paints the buttons: menu, log and chat
	 * 
	 * @throws SlickException
	 */
	private void paintButtons() throws SlickException {
		int xOffset = 110;
		int yOffset = 60;
		int buttonHeight = menuButton.getHeight();
		int buttonWidth = menuButton.getWidth();
		int buttonSpacing = 10;
		menuButton.draw(gameContainerWidth - xOffset, gameContainerHeight
				- (yOffset + buttonSpacing));
		chatButton.draw(gameContainerWidth - xOffset, gameContainerHeight
				- (2 * yOffset + buttonSpacing));
		logButton.draw(gameContainerWidth - xOffset, gameContainerHeight
				- (3 * yOffset + buttonSpacing));
		nextButton.draw(gameContainerWidth - 2 * xOffset, gameContainerHeight
				- (yOffset + buttonSpacing + 50));
		playAllButton.draw(gameContainerWidth - xOffset, gameContainerHeight
				- (4 * yOffset + buttonSpacing));
		menuRec = new Rectangle(gameContainerWidth - xOffset,
				gameContainerHeight - (yOffset + buttonSpacing), buttonWidth,
				buttonHeight);
		chatRec = new Rectangle(gameContainerWidth - xOffset,
				gameContainerHeight - (2 * yOffset + buttonSpacing),
				buttonWidth, buttonHeight);
		logRec = new Rectangle(gameContainerWidth - xOffset,
				gameContainerHeight - (3 * yOffset + buttonSpacing),
				buttonWidth, buttonHeight);
		nextRec = new Rectangle(gameContainerWidth - 2 * xOffset,
				gameContainerHeight - (yOffset + buttonSpacing + 50), 100, 100);
		playAllRec = new Rectangle(gameContainerWidth - xOffset,
				gameContainerHeight - (4 * yOffset + buttonSpacing),
				buttonWidth, buttonHeight);
	}

	/**
	 * Paints the counterZone i.e the status bar
	 * 
	 * @throws slickException
	 */
	private void paintStatusBar(Graphics g) throws SlickException {
		statusBar.draw(0, (float) gameContainerHeight - gameContainerHeight / 3
				- 60, gameContainerWidth, 46);
		g.setColor(Color.white);
		g.drawString("Actions: " + actions, 50, gameContainerHeight
				- gameContainerHeight / 3 - 45);
		g.drawString("Buys: " + buys, 170, gameContainerHeight
				- gameContainerHeight / 3 - 45);
		g.drawString("x" + money, 300, gameContainerHeight
				- gameContainerHeight / 3 - 45);
		g.drawString("Top discard: " + topOfPile, 850, gameContainerHeight
				- gameContainerHeight / 3 - 45);
		g.drawString("Cards in deck: " + deckSize, 1100, gameContainerHeight
				- gameContainerHeight / 3 - 45);
		riksdaler.draw((float) 270, (float) gameContainerHeight
				- gameContainerHeight / 3 - 45, (float) 0.03);
		g.drawString(tipMessage, 490, gameContainerHeight - gameContainerHeight
				/ 3 - 50);
	}

	/**
	 * Paints a card in the play zone, Maybe add animation if played from your
	 * own hand
	 * 
	 * @param card
	 * @throws SlickException
	 */
	private void paintPlayedCards(Image[] cards) {
		float cardHeight;
		double scale;
		float cardWidth;

		for (int i = 0; i < cards.length; i++) {
			cardWidth = (float) gameContainerWidth / 14;
			scale = (double) cardWidth / cards[i].getWidth();
			cardHeight = (float) (cards[i].getHeight() * scale);
			int xOffset = gameContainerWidth / 2 - (int) cardWidth / 2
					- (int) ((cardWidth * 3 / 4) * i);
			int yOffset = gameContainerHeight - gameContainerWidth / 3 - 20;

			cards[i].draw(xOffset, yOffset, cardWidth, cardHeight);
		}
	}

	/**
	 * Resets the rectangles that are used as listeners in the players hand.
	 * 
	 * <p>
	 * If they are not reset and a formerly occupied place is clicked, a
	 * rectangle will remain and try to access the players hand outside bounds,
	 * and generate a crash.
	 * <p>
	 */
	private void resetHandRectangles() {
		for (int i = 0; i < handRectangles.length; i++) {
			handRectangles[i] = new Rectangle();
		}
	}

	/**
	 * Return the number of cards that are in the supply of the cards supplied.
	 * 
	 * The method returns an array where every index is connected to its parent
	 * array. This means that if index 0 returns 12, there exist 12 cards of the
	 * one in index 0 in the parent array.
	 * 
	 * @param cards
	 *            cards that exist within the {@link Supply}
	 * @return an array with the number of cards
	 */
	private int[] getNbrOfCards(String cards[]) {
		int[] nbrOfCards = new int[cards.length];
		for (int i = 0; i < cards.length; i++) {
			nbrOfCards[i] = supply.get(cards[i]);
		}
		return nbrOfCards;
	}

	/**
	 * A metod for painting messageboxes TODO: add yes/no boxes and rectangles.
	 * 
	 * @param message
	 *            , the message to be written in the box
	 * @param g
	 */
	private void paintMessageBox(String message, Graphics g) {
		int x = gameContainerWidth - 270;
		int y = gameContainerHeight - gameContainerHeight / 3 - 230;
		messageBox.draw(x, y, 250, 150);
		g.drawString(message, x + 20, y + 20);
	}

	/**
	 * Paints numbers that show the amount of cards left in the supply.
	 * 
	 * <p>
	 * These numbers will appear in the top left corner with filled circle
	 * behind to create contrast. It is important that the number arrays were
	 * generated using {@link #getNbrOfCards(String[]) getNbrOfCards} and that
	 * the image array is the same that was used when generated.
	 * </p>
	 * 
	 * @param numbers
	 *            an array with the numbers to be painted
	 * @param cards
	 *            the cards that are related to the numbers
	 * @param g
	 *            graphics that will be used when painting
	 */
	private void paintNbrOfActionCards(int[] numbers, Image[] cards, Graphics g) {
		float cardHeight;
		double scale;
		float cardWidth;
		int xOffset = 15 + gameContainerWidth / 7;
		int cardSpacing = 5;
		int yOffset = 5;

		for (int i = 0; i < cards.length; i++) {
			cardWidth = (float) (gameContainerWidth / 9);
			scale = (double) cardWidth / cards[i].getWidth();
			cardHeight = (float) (cards[i].getHeight() * scale);
			int orbOffset = 3;

			if (i < cards.length / 2) {
				g.setColor(Color.black);
				g.fillOval(xOffset + cardWidth * (i) + (i * cardSpacing)
						- orbOffset, yOffset - orbOffset, 24, 24);
				g.setColor(Color.white);
				g.drawString("" + numbers[i], xOffset + cardWidth * (i)
						+ (i * cardSpacing), yOffset);
			} else {
				g.setColor(Color.black);
				g.fillOval(xOffset + cardWidth * (i - 5)
						+ ((i - 5) * cardSpacing) - orbOffset, cardHeight + 2
						* yOffset - orbOffset, 24, 24);
				g.setColor(Color.white);
				g.drawString("" + numbers[i], xOffset + cardWidth * (i - 5)
						+ ((i - 5) * cardSpacing), cardHeight + 2 * yOffset);
			}
		}
	}

	/**
	 * Paints numbers that show the amount of cards left in the supply.
	 * 
	 * <p>
	 * These numbers will appear in the top left corner with filled circle
	 * behind to create contrast. It is important that the number arrays were
	 * generated using {@link #getNbrOfCards(String[]) getNbrOfCards} and that
	 * the image array is the same that was used when generated.
	 * </p>
	 * 
	 * @param numbers
	 *            an array with the numbers to be painted
	 * @param cards
	 *            the cards that are related to the numbers
	 * @param g
	 *            graphics that will be used when painting
	 */
	private void paintNbrOfTreasureCards(int[] numbers, Image[] cards,
			Graphics g) {
		float cardHeight;
		double scale;
		float cardWidth;
		int yOffset = 5;
		int cardSpacing = 5;
		int orbOffset = 3;

		for (int i = 0; i < cards.length; i++) {
			cardWidth = (float) gameContainerWidth / 14;
			scale = (double) cardWidth / cards[i].getWidth();
			cardHeight = (float) (cards[i].getHeight() * scale);
			int xOffset = (int) (cardWidth + 10);

			g.setColor(Color.black);
			g.fillOval(xOffset - orbOffset, yOffset + cardHeight * i
					+ (i * cardSpacing) - orbOffset, 24, 24);
			g.setColor(Color.white);
			g.drawString("" + numbers[i], xOffset, yOffset + cardHeight * i
					+ (i * cardSpacing));
		}
	}

	/**
	 * Paints numbers that show the amount of cards left in the supply.
	 * 
	 * <p>
	 * These numbers will appear in the top left corner with filled circle
	 * behind to create contrast. It is important that the number arrays were
	 * generated using {@link #getNbrOfCards(String[]) getNbrOfCards} and that
	 * the image array is the same that was used when generated.
	 * </p>
	 * 
	 * @param numbers
	 *            an array with the numbers to be painted
	 * @param cards
	 *            the cards that are related to the numbers
	 * @param g
	 *            graphics that will be used when painting
	 */
	private void paintNbrOfVictoryCards(int[] numbers, Image[] cards, Graphics g) {
		float cardHeight;
		double scale;
		float cardWidth;
		int xOffset = 5;
		int yOffset = 5;
		int cardSpacing = 5;
		int orbOffset = 3;

		for (int i = 0; i < cards.length; i++) {
			cardWidth = (float) gameContainerWidth / 14;
			scale = (double) cardWidth / cards[i].getWidth();
			cardHeight = (float) (cards[i].getHeight() * scale);

			g.setColor(Color.black);
			g.fillOval(xOffset - orbOffset, cardHeight * i + (i * cardSpacing)
					+ yOffset - orbOffset, 24, 24);
			g.setColor(Color.white);
			g.drawString("" + numbers[i], xOffset, cardHeight * i
					+ (i * cardSpacing) + yOffset);
		}
	}

	/**
	 * Paints the relevant info of the opposing players.
	 * 
	 * <p>
	 * Maybe this method should take parameters in the future. I just don't
	 * know. It's so dark here. I'm afraid.
	 * </p>
	 * 
	 */
	private void paintOpposingPlayers(Graphics g) {
		int xOffset = 1050;
		int yOffsetName = 15;
		int yOffsetHand = 30;
		int space = 50;
		int numberPainted = 0;

		for (int i = 0; i < playerNames.length; i++) {
			if (!playerNames[i].equals(Settings.getName())) {
				if (i == turn) {
					g.setColor(Color.red);
				} else {
					g.setColor(Color.white);
				}
				g.drawString("Player: " + playerNames[i], xOffset, yOffsetName
						+ space * numberPainted);
				g.setColor(Color.white);
				g.drawString("Cards on hand: " + cardsOnHand[i], xOffset,
						yOffsetHand + space * numberPainted);
				numberPainted++;
			}
		}
	}

	/**
	 * A method for painting the log and it's messages. It currently draws one
	 * string on each line disregarding its length. TODO: Implement scrolling
	 * and text wrapping.
	 * 
	 * @param g
	 */
	private void paintLog(Graphics g) {
		int xOffset = 930;
		int yOffset = 120;
		int textOffset = 55;
		int textSpacing = -15;
		int numberOfTexts = 0;
		float scale = (float) 0.85;

		if (logDisplay) {
			log.draw(xOffset, yOffset, scale);
			for (String str : logText) {
				if (str != null && numberOfTexts < 15) {
					g.drawString(str, xOffset + 20,
							(textSpacing * numberOfTexts) + yOffset
									- textOffset
									+ (int) (log.getHeight() * 0.85));
					numberOfTexts++;
				}
			}
		}
	}

	/**
	 * @param supply
	 *            the supply to set
	 * @throws SlickException
	 */
	public void setSupply(HashMap<String, Integer> supply) {
		this.supply = supply;

		moveSupplyImages();
		try {
			initAmounts();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets all players' names.
	 */
	public void setPlayerNames(String[] names) {
		playerNames = names;
	}

	/**
	 * Since we have to pre-load all images we move them here seperately when we
	 * know what cards that will be used.
	 */
	private void moveSupplyImages() {
		LinkedList<Image> moved = new LinkedList<Image>();
		for (String key : supply.keySet()) {
			for (int i = 0; i < actionCardsAll.length; i++) {
				String name = splitString(actionCardsAll[i]);
				if (name.equals(key)) {
					moved.add(actionCardsAll[i]);
				}
			}

		}
		actionCards = moved.toArray(new Image[0]);
	}

	public void setPhase(String phase) {
		this.phase = phase;
		this.addLogMessage(phase + " phase entered.");
	}

}
