package tda367.dominion.client.view;

import java.util.List;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.state.StateBasedGame;

import tda367.dominion.server.controller.ClientController;
import tda367.dominion.server.model.CardInfoHandler;
import tda367.dominion.server.model.Pile;
import tda367.dominion.server.model.Player;
import tda367.dominion.server.model.Supply;

public class InGameState extends ControlledGameState {

	private Supply supply;
	private Player player;
	private CardInfoHandler cih;
	private Image[] actionCards;
	private Image[] victoryCards;
	private Image[] treasureCards;
	private Image[] playedCards;
	private Image board = null;
	private int gameContainerWidth;
	private int gameContainerHeight;
	private int amountOfPlayers;
	private RoundedRectangle counterZone;
	private String nmbOfActions;
	private String nmbOfBuys;
	private String nmbOfRiksdaler;
	private Image riksdaler = null;
	private Rectangle[] actionRectangles;
	private Rectangle[] victoryRectangles;
	private Rectangle[] treasureRectangles;
	private Rectangle[] handRectangles;
	private Image menuButton;
	private Image chatButton;
	private Image logButton;
	private Image nextButton;
	private Rectangle menuRec;
	private Rectangle chatRec;
	private Rectangle logRec;
	private Rectangle nextRec;
	private boolean enterShowCard;//Temporary
	private Image cardToShow;//Temporary
	
	public InGameState(int id, ClientController controller) {
		super(id, controller);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		enterShowCard = false;
		
		amountOfPlayers = 2; //Should probably be supplied from network later
		supply = new Supply(amountOfPlayers);
		player = new Player("Mr.Testificate");
		player.addToHand("Thief");
		actionCards = StringArraytoImageArray(getActionCards(getSupply()));
		victoryCards = StringArraytoImageArray(getVictoryCards(getSupply()));
		treasureCards = StringArraytoImageArray(getTreasureCards(getSupply()));
		counterZone = new RoundedRectangle(0, gc.getHeight() - gc.getHeight()/3 - 50, gc.getWidth(), 40, 2);

		gameContainerWidth = gc.getWidth();
		gameContainerHeight = gc.getHeight();
		
		//Initiate all images
		menuButton = new Image("res/img/gui/ingame/MenuButton.png");
		chatButton = new Image("res/img/gui/ingame/ChatButton.png");
		logButton = new Image("res/img/gui/ingame/LogButton.png");
		riksdaler = new Image("res/img/gui/ingame/Coin.png");
		board = new Image("res/img/gui/ingame/BoardTemp.png");
		nextButton = new Image("res/img/gui/ingame/NextButton.png");
		
		//Initiate all rectangles
		actionRectangles = new Rectangle[10];
		victoryRectangles = new Rectangle[4];
		treasureRectangles = new Rectangle[3];
		handRectangles = new Rectangle[40];
		for (int i = 0; i<10; i++) {
			actionRectangles[i] = new Rectangle();
		}
		for (int i = 0; i<4; i++) {
			victoryRectangles[i] = new Rectangle();
		}
		for (int i = 0; i<3; i++) {
			treasureRectangles[i] = new Rectangle();
		}
		for(int i = 0; i<40; i++) {
			handRectangles[i] = new Rectangle();
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		board.draw();	
		paintVictoryCards(victoryCards);
		paintActionCards(actionCards);
		paintTreasureCards(treasureCards);
		paintPlayerHand(actionCards);
		paintButtons();
		paintCounterZone(g);
		
		if(playedCards!=null) { 
			paintPlayedCards(playedCards);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int time)
			throws SlickException {
		
		Input input  = gc.getInput();
		gameContainerWidth = gc.getWidth();
		gameContainerHeight = gc.getHeight();
		
		if(player.getPlayedCards().length>0) {
			playedCards = StringArraytoImageArray(player.getPlayedCards());
		} else {
			playedCards = null;
		}
		
		//Update values
		nmbOfActions = String.valueOf(player.getActions());
		nmbOfBuys = String.valueOf(player.getBuys());
		nmbOfRiksdaler = String.valueOf(player.getMoney());		
		
		if(enterShowCard){
			enterShowCard = false;
			String temp = cardToShow.getResourceReference();
			cardToShow = new Image(temp.split("Supply")[0]+".jpg");
			((ShowCardState)sbg.getState(MainView.SHOWCARDSTATE)).showCard(cardToShow);
			sbg.enterState(MainView.SHOWCARDSTATE);
		}
		
		//Return to menu
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			sbg.enterState(1);
		}
		
	}
	
	/**
	 * Takes care of all actions that calls mouseClicked
	 */
	@Override
	public void mouseClicked(int button, int x, int y, int clicks) {
		super.mouseClicked(button, x, y, clicks);
		//Victory cards listener
		for(int i=0; i<4; i++) {
			if(button == Input.MOUSE_LEFT_BUTTON && victoryRectangles[i].contains(x, y)) {
				System.out.println("Victory Card: " + (i+1));
			} else if(button == Input.MOUSE_RIGHT_BUTTON && victoryRectangles[i].contains(x, y)){//Checking for detailed view
				enterShowCard = true;
				cardToShow = victoryCards[i];
			}
		}
		
		//Treasure cards listener
		for(int i=0; i<3; i++) {
			if(button == Input.MOUSE_LEFT_BUTTON && treasureRectangles[i].contains(x,y)) {
				System.out.println("Treasure card: " + (i+1));
			} else if(button == Input.MOUSE_RIGHT_BUTTON && treasureRectangles[i].contains(x, y)){//Checking for detailed view
				enterShowCard = true;
				cardToShow = treasureCards[i];
			}
		}
		
		//Action cards listener
		for(int i=0; i<10; i++) {
			if(button == Input.MOUSE_LEFT_BUTTON && actionRectangles[i].contains(x,y)) {
				System.out.println("Action card: " + (i+1));
			} else if(button == Input.MOUSE_RIGHT_BUTTON && actionRectangles[i].contains(x, y)){//Checking for detailed view
				enterShowCard = true;
				cardToShow = actionCards[i];
			}
		}
		
		//Hand cards listener
		for(int i=0; i<handRectangles.length; i++) {
			if(button == Input.MOUSE_LEFT_BUTTON && handRectangles[i].contains(x,y)) {
				System.out.println("Hand card: " + player.revealHand().get(i));
				player.play(i);
				resetHandRectangles();
			} else if(button == Input.MOUSE_RIGHT_BUTTON && handRectangles[i].contains(x, y)){//Checking for detailed view
				//enterShowCard = true;
				//cardToShow = handCards[i];
			}
		}
		
		//Menu button listener
		if(button == Input.MOUSE_LEFT_BUTTON && menuRec.contains(x, y)) {
			System.out.println("Menu button");
		}
		
		//Chat button listener
		if(button == Input.MOUSE_LEFT_BUTTON && chatRec.contains(x, y)) {
			System.out.println("Chat Button");
		}
		
		//Log button listener
		if(button == Input.MOUSE_LEFT_BUTTON && logRec.contains(x, y)) {
			System.out.println("Log Button");
		}
		
		//Next/End button listener
		if(button == Input.MOUSE_LEFT_BUTTON && nextRec.contains(x, y)) {
			try {
				if(nextButton.getResourceReference() == "res/img/gui/ingame/NextButton.png") {
					nextButton = new Image("res/img/gui/ingame/EndTurnButton.png");
				} else {
					player.cleanUp();
					nextButton = new Image("res/img/gui/ingame/NextButton.png");
				}
			} catch(SlickException s) {
				
			}
		}
	}

	/**
	 * This method is called every time this state is left.
	 * 
	 * <p>Its current behavior is that it clears the record of 
	 * keys pressed, removing any unwanted behavior in states
	 * switched to.<p/>
	 * 
	 * @param gc the {@link GameContainer} this state is contained in
	 * @param sbg the {@link StateBasedGame} this state is a part of
	 */
	@Override
	public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
		  gc.getInput().clearKeyPressedRecord();
	}

	/**
	 * Returns all the cardnames in the supply.
	 * 
	 * <p>In future updates, this should probably also do 
	 * something sensible with the amount of cards left in 
	 * the supply. But we'll get to that when we have to.</p>
	 * 
	 * @return every cardname in the supply
	 */
	private String[] getSupply() {
		return supply.getCardsInSupply().keySet().toArray(new String[0]);
	}
	
	/**
	 * Returns all the actioncards from an array of cardnames.
	 * 
	 * <p> Although this method promises to return all actioncards, it will also
	 * return a copy of gardens if one is present in the string of cardnames.
	 * This is because a copy of gardens should be displayed with the actioncards
	 * in the supply, and the true purpose of this method is the get the ten cards
	 * that should be displayed in the supply.</p>
	 * 
	 * <p>Worthy of mention is that the cards are retured sorted in descending
	 * order, i.e. the most expensive first.</p>
	 * 
	 * @param cards the array of cardnames that will be searched for actioncards
	 * @return an array containing every actioncard found
	 */
	private String[] getActionCards(String[] cards) {
		cih = CardInfoHandler.getInstance();
		List<String> allActionCards = cih.getActionCards();
		String[] cardsToReturn = new String[10];
		
		int index = 0;
		for(int i = 0; i < cards.length; i++){
			if(allActionCards.contains(cards[i])){
				cardsToReturn[index] = cards[i];
				index++;
			}
		}
		
		for(int i = 0; i < cards.length; i++){
			if(cards[i].equals("Gardens")){//Ensures Gardens DOES end up with the actioncards
				cardsToReturn[index] = cards[i];
			}
		}
		
		return sortStringArray(cardsToReturn);
	}
	
	/**
	 * Returns all the victorycards from an array of cardnames
	 * 
	 * <p>Although it says victorycards, this method will also fetch curse
	 * cards, as they are somewhat related, at least when they will be drawn 
	 * out in the supply are.</p>
	 * 
	 * <p>Worthy of mention is that the cards are retured sorted in descending
	 * order, i.e. the most expensive first.</p>
	 * 
	 * @param cards the array of cardnames that will be searched for victorycards
	 * @return an array containing every victorycard found
	 */
	private String[] getVictoryCards(String[] cards) {
		cih = CardInfoHandler.getInstance();
		List<String> allVictoryCards = cih.getVictoryCards();
		String[] cardsToReturn = new String[4];
		
		int index = 0;
		for(int i = 0; i < cards.length; i++){
			if(allVictoryCards.contains(cards[i])){
				if(!cards[i].equals("Gardens")){//Ensures Gardens does NOT end up with the victorycards
					cardsToReturn[index] = cards[i];
					index++;
				}
			}
		}
		
		for(int i = 0; i < cards.length; i++){
			if(cards[i].equals("Curse")){//Ensures that Curse DOES end up with the victorycards
				cardsToReturn[index] = cards[i];
			}
		}
		
		return sortStringArray(cardsToReturn);
	}
	 /**
	  * Returns all the treasurecards from an array of cardnames.
	  * 
	  * <p>The cards are returned in descending order, i.e. highest cost first.</p>
	  * 
	  * @param cards the array of cardnames that will be searched for treasurecards
	  * @return an array containg every treasurecard found
	  */
	private String[] getTreasureCards(String[] cards) {
		cih = CardInfoHandler.getInstance();
		List<String> allTreasureCards = cih.getTreasureCards();
		String[] cardsToReturn = new String[3];
		
		int index = 0;
		for(int i = 0; i < cards.length; i++){
			if(allTreasureCards.contains(cards[i])){
				cardsToReturn[index] = cards[i];
				index++;
			}
		}
		
		return sortStringArray(cardsToReturn);
	}
	
	/**
	 * This method takes an array of cardnames in the form of strings
	 * and returns an array with the path to the image of these cards.
	 * 
	 * <p>Note that this method is only useable for string arrays with
	 * cardnames, and nothing else. While you can send in any kind of 
	 * String array, anything but the recommended will not result in 
	 * anything but a garbled heap of crap.</p>
	 * 
	 * @param cards
	 * @return
	 */
	private Image[] StringArraytoImageArray(String[] cards) 
			throws SlickException {
		Image[] imageArray = new Image[cards.length];
		cih = CardInfoHandler.getInstance();
		
		for(int i = 0; i < cards.length; i++){
			imageArray[i] = new Image(cih.getCroppedImageLink(cards[i]));
		}
		
		return imageArray;
	}
	
	/**
	 * Returns a sorted clone of the given String array containing cardnames.
	 * 
	 * <p>This method is only good for String arrays containing cardnames,
	 * much like {@link StringArrayToImageArray}. This method sorts in desscending order
	 * based in cost, i.e. the most expensive card is the first element.</p>
	 * 
	 * @param cards an array of strings containing cardnames
	 * @return a clone of the given array in sorted order.
	 */
	private String[] sortStringArray(String[] cards) {
		cih = CardInfoHandler.getInstance();
		String[] sortedArray = cards.clone();
		
		for(int threshold = sortedArray.length-1; threshold > 0; threshold--){
			for(int i = 0; i < threshold; i++){
				if(cih.getCardValue(sortedArray[(i+1)]) > cih.getCardValue(sortedArray[i])){
					String temp = sortedArray[i];
					sortedArray[i] = sortedArray[(i+1)];
					sortedArray[(i+1)] = temp;
				}
			}
		}
		
		return sortedArray;
	}
	
	/**
	 * Paints the victorycards in appropriate places. Also sets the location of 
	 * victoryRectangles to the same places.
	 * 
	 * <p>Although any sort of image array may be passed
	 * to this method, it is tailored for images of dominion
	 * cards, specifically those of the victory category,
	 * and nothing else.</p>
	 * 
	 * <p>Also note that victorycards in this sense includes
	 * the Curse-card, but excludes Gardens, and that the size of the array should
	 * be 4. If it is not, this method cannot be guaranteed
	 * to work properly.</p>
	 * 
	 * @param cards the images to be painted
	 */
	private void paintVictoryCards(Image[] cards) {
		float cardHeight;
		double scale;
		float cardWidth;
		int xOffset = 5;
		int yOffset = 5;
		int cardSpacing = 5;
		
		for(int i = 0; i < cards.length; i++){
			cardWidth = (float) gameContainerWidth/14;
			scale = (double) cardWidth/cards[i].getWidth();
			cardHeight = (float) (cards[i].getHeight()*scale);
			
			cards[i].draw(xOffset, cardHeight*i+(i*cardSpacing)+yOffset, cardWidth, cardHeight);
			victoryRectangles[i].setLocation(xOffset, (int)cardHeight*i+(i*cardSpacing)+yOffset);
			victoryRectangles[i].setSize((int)cardWidth, (int)cardHeight);
		}
	}
	
	/**
	 * Paints the actioncards in appropriate places. Also places
	 * actionRectangles in the same places. 
	 * 
	 * <p>Although any sort of image array may be passed
	 * to this method, it is tailored for images of dominion
	 * cards, specifically those of the action category,
	 * and nothing else.</p>
	 * 
	 * <p>Also note that action cards in this sense includes
	 * Gardens. The size of the array should be 10. If it is not, 
	 * this method cannot be guaranteed to work properly.</p>
	 * 
	 * @param cards the images to be painted
	 */
	private void paintActionCards(Image[] cards) {
		float cardHeight;
		double scale;
		float cardWidth;
		int xOffset = 15 + gameContainerWidth/7;
		int cardSpacing = 5;
		int yOffset = 5;
		
		for(int i = 0; i < cards.length; i++){
			cardWidth = (float) (gameContainerWidth/9);
			scale = (double) cardWidth/cards[i].getWidth();
			cardHeight = (float) (cards[i].getHeight()*scale);
			
			if(i < cards.length/2){
				cards[i].draw(xOffset + cardWidth*(i)+(i*cardSpacing), yOffset, cardWidth, cardHeight);
				actionRectangles[i].setLocation((int)(xOffset + cardWidth*(i)+(i*cardSpacing)), yOffset);
				actionRectangles[i].setSize((int)cardWidth, (int)cardHeight + 5);
			} else {
				cards[i].draw(xOffset + cardWidth*(i-5)+((i-5)*cardSpacing), cardHeight + 2*yOffset, cardWidth, cardHeight);
				actionRectangles[i].setLocation((int)(xOffset + cardWidth*(i-5)+(i*cardSpacing)), (int)cardHeight + 2*yOffset);
				actionRectangles[i].setSize((int)cardWidth, (int)cardHeight);
			}
		}
	}
	
	/**
	 * Paints the treasurecards in appropriate places. Also places
	 * treasureRectangles in the same places.
	 * 
	 * <p>Although any sort of image array may be passed
	 * to this method, it is tailored for images of dominion
	 * cards, specifically those of the action category,
	 * and nothing else.</p>
	 * 
	 * <p>Also note that the size of the array should be 3.
	 * If it is not, this method cannot be guaranteed to 
	 * work properly.</p>
	 * 
	 * @param cards
	 */
	private void paintTreasureCards(Image[] cards) {
		float cardHeight;
		double scale;
		float cardWidth;
		int yOffset = 5;
		int cardSpacing = 5;
		
		for(int i = 0; i < cards.length; i++){
			cardWidth = (float) gameContainerWidth/14;
			scale = (double) cardWidth/cards[i].getWidth();
			cardHeight = (float) (cards[i].getHeight()*scale);
			int xOffset = (int) (cardWidth+10);
			
			cards[i].draw(xOffset, yOffset+cardHeight*i+(i*cardSpacing), cardWidth, cardHeight);
			treasureRectangles[i].setLocation((int)(xOffset), (int)cardHeight*i+(i*cardSpacing)+yOffset);
			treasureRectangles[i].setSize((int)cardWidth, (int)cardHeight);
		}
	}
	
	/**
	 * Paints the cards in the players hand.
	 * 
	 * <p>Although any sort of image array may be passed 
	 * to thus method, it is tailored for images of dominion 
	 * cards, and giving it anything else may result in
	 * unwanted behaviour.</p>
	 * 
	 * @param cards
	 */
	private void paintPlayerHand(Image[] cards) 
			throws SlickException {
		cih = CardInfoHandler.getInstance();
		Pile p = player.getHand();
		String[] stringCards = p.getCards().toArray(new String[0]);
		Image[] imageCards = new Image[stringCards.length];
		
		for(int i = 0; i < stringCards.length; i++){
			imageCards[i] = new Image(cih.getImageLink(stringCards[i]));
		}
		
		for(int i = 0; i < imageCards.length; i++){
			float cardHeight = (float) gameContainerHeight*((float)1/3);
			double scale = (double) cardHeight/imageCards[i].getHeight();
			float cardWidth = (float) (imageCards[i].getWidth()*scale);
			
			imageCards[i].draw(cardWidth*i+(10*(i+1)), gameContainerHeight - cardHeight - 10, cardWidth, cardHeight);
			handRectangles[i].setLocation((int)cardWidth*i+(10*(i+1)), (int)(gameContainerHeight - cardHeight - 10));
			handRectangles[i].setSize((int)cardWidth, (int)cardHeight);
		}
		
	}
	
	/**
	 * Paints the buttons: menu, log and chat
	 * @throws SlickException
	 */
	private void paintButtons() 
			throws SlickException {
		int xOffset = 110;
		int yOffset = 60;
		int buttonHeight = menuButton.getHeight();
		int buttonWidth = menuButton.getWidth();
		int buttonSpacing = 10;
		menuButton.draw(gameContainerWidth - xOffset, gameContainerHeight - (yOffset + buttonSpacing));
		chatButton.draw(gameContainerWidth - xOffset, gameContainerHeight - (2*yOffset + buttonSpacing));
		logButton.draw(gameContainerWidth - xOffset, gameContainerHeight - (3*yOffset + buttonSpacing));
		nextButton.draw(gameContainerWidth - 2*xOffset, gameContainerHeight - (yOffset + buttonSpacing + 50));
		menuRec = new Rectangle(gameContainerWidth - xOffset, gameContainerHeight - (yOffset + buttonSpacing), buttonWidth, buttonHeight);
		chatRec = new Rectangle(gameContainerWidth - xOffset, gameContainerHeight - (2*yOffset + buttonSpacing), buttonWidth, buttonHeight);
		logRec = new Rectangle(gameContainerWidth - xOffset, gameContainerHeight - (3*yOffset + buttonSpacing), buttonWidth, buttonHeight);
		nextRec = new Rectangle(gameContainerWidth - 2*xOffset, gameContainerHeight - (yOffset + buttonSpacing + 50), 100, 100);
	}
	
	/**
	 * Paints the counterZone i.e the status bar
	 * @throws slickException
	 */
	private void paintCounterZone(Graphics g) 
			throws SlickException {
		counterZone.setBounds((float)3, (float)gameContainerHeight - gameContainerHeight/3 - 60, gameContainerWidth - 5, 40);
		g.setLineWidth(5);
		g.setColor(Color.darkGray);
		g.draw(counterZone);
		g.setColor(Color.white);
		g.drawString("Actions: " + nmbOfActions, 50, gameContainerHeight - gameContainerHeight/3 - 50);
		g.drawString("Buys: " + nmbOfBuys, 200, gameContainerHeight - gameContainerHeight/3 - 50);
		g.drawString("x"+nmbOfRiksdaler, 380, gameContainerHeight - gameContainerHeight/3 - 50);
		riksdaler.draw((float)350, (float)gameContainerHeight - gameContainerHeight/3 - 53, (float)0.035);
	}
	
	/**
	 * Paints a card in the play zone, Maybe add animation if played from your own hand
	 * @param card
	 * @throws SlickException
	 */
	private void paintPlayedCards(Image[] cards) {
		float cardHeight;
		double scale;
		float cardWidth;
		
		for(int i = 0; i < cards.length; i++){
			cardWidth = (float) gameContainerWidth/14;
			scale = (double) cardWidth/cards[i].getWidth();
			cardHeight = (float) (cards[i].getHeight()*scale);
			int xOffset = gameContainerWidth/2 - (int)cardWidth/2 - (int)((cardWidth*3/4)*i);
			int yOffset = gameContainerHeight - gameContainerWidth/3 - 20;
			
			cards[i].draw(xOffset, yOffset, cardWidth, cardHeight);
		}
	}
	
	private void resetHandRectangles() {
		for(int i = 0; i < handRectangles.length; i++){
			handRectangles[i] = new Rectangle();
		}
	}

}