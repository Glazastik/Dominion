package tda367.dominion.view;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import tda367.dominion.model.CardInfoHandler;
import tda367.dominion.model.Player;
import tda367.dominion.model.Supply;

public class InGameViewState extends BasicGameState {

	private Supply supply;
	private Player player;
	private CardInfoHandler cih;
	private Image[] actionCards;
	private Image[] victoryCards;
	private Image[] treasureCards;
	private Image board = null;
	private int gameContainerWidth;
	private int id = 0;
	private int amountOfPlayers;
	private int ROWS_IN_SUPPLY;
	private RoundedRectangle counterZone = null;
	private String nmbOfActions;
	private String nmbOfBuys;
	private String nmbOfRiksdaler;
	private Image riksdaler = null;
	
	public InGameViewState(int id) {
		this.id = id;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		board = new Image("res/img/gui/ingame/BoardTemp.png");
		ROWS_IN_SUPPLY = 7;
		amountOfPlayers = 2; //Should probably be supplied from network later
		supply = new Supply(amountOfPlayers);
		player = new Player("Mr.Testificate");
		actionCards = StringArraytoImageArray(getActionCards(getSupply()));
		victoryCards = StringArraytoImageArray(getVictoryCards(getSupply()));
		treasureCards = StringArraytoImageArray(getTreasureCards(getSupply()));
		counterZone = new RoundedRectangle(0, gc.getHeight() - gc.getHeight()/3 - 50, gc.getWidth(), 40, 2);
		riksdaler = new Image("res/img/gui/ingame/Coin.png");
		gameContainerWidth = gc.getWidth();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		gameContainerWidth = gc.getWidth();
		board.draw();	
		g.setLineWidth(10);
		g.setColor(Color.darkGray);
		g.drawString("InGameState", 0, 0);
		g.draw(counterZone);
		g.setColor(Color.white);
		g.drawString("Actions: " + nmbOfActions, 50, gc.getHeight() - gc.getHeight()/3 - 40);
		g.drawString("Buys: " + nmbOfBuys, 200, gc.getHeight() - gc.getHeight()/3 - 40);
		g.drawString("x"+nmbOfRiksdaler, 380, gc.getHeight() - gc.getHeight()/3 - 40);
		riksdaler.draw((float)350, (float)gc.getHeight() - gc.getHeight()/3 - 43, (float)0.035);
		paintVictoryCards(victoryCards);
		paintActionCards(actionCards);
		paintTreasureCards(treasureCards);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int time)
			throws SlickException {
		player.increaseMoney(1);
		
		nmbOfActions = String.valueOf(player.getActions());
		nmbOfBuys = String.valueOf(player.getBuys());
		nmbOfRiksdaler = String.valueOf(player.getMoney());
		
		Input input  = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			sbg.enterState(1);
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

	@Override
	public int getID() {
		return id;
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
	 * Paints the victorycards in appropriate places.
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
		int cardHeight;
		double scale;
		int cardWidth;
		
		for(int i = 0; i < cards.length; i++){
			cardWidth = gameContainerWidth/ROWS_IN_SUPPLY;
			scale = (double) cardWidth/cards[i].getWidth();
			cardHeight = (int) (cards[i].getHeight()*scale);
			cards[i].draw(0, cardHeight*i, cardWidth, cardHeight);
		}
	}
	
	/**
	 * Paints the actioncards in appropriate places.
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
		int cardHeight;
		double scale;
		int cardWidth;
		
		for(int i = 0; i < cards.length; i++){
			cardWidth = gameContainerWidth/ROWS_IN_SUPPLY;
			scale = (double) cardWidth/cards[i].getWidth();
			cardHeight = (int) (cards[i].getHeight()*scale);
			if(i < cards.length/2){
				cards[i].draw(cardWidth*(i+1), 0, cardWidth, cardHeight);
			} else {
				cards[i].draw(cardWidth*(i+1-5), cardHeight, cardWidth, cardHeight);
			}
		}
	}
	
	/**
	 * Paints the treasurecards in appropriate places.
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
		int cardHeight;
		double scale;
		int cardWidth;
		
		for(int i = 0; i < cards.length; i++){
			cardWidth = gameContainerWidth/ROWS_IN_SUPPLY;
			scale = (double) cardWidth/cards[i].getWidth();
			cardHeight = (int) (cards[i].getHeight()*scale);
			cards[i].draw(cardWidth*6, cardHeight*i, cardWidth, cardHeight);
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
	private void paintPlayerHand(Image[] cards){
		
	}

}
