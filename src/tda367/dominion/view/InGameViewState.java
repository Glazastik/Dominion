package tda367.dominion.view;

import tda367.dominion.model.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.state.*;

public class InGameViewState extends BasicGameState {

	Supply supply;
	CardInfoHandler cih;
	Image[] actionCards;
	Image[] victoryCards;
	Image[] treasureCards;
	Image[] cardsToShow;
	Image board = null;
	int id = 0;
	int amountOfPlayers;
	RoundedRectangle counterZone = null;
	
	public InGameViewState(int id) {
		this.id = id;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		board = new Image("res/img/gui/ingame/BoardTemp.png");
		amountOfPlayers = 2; //Should probably be supplied from network later
		supply = new Supply(amountOfPlayers);
		getSupply();
		counterZone = new RoundedRectangle(0, gc.getHeight()-gc.getHeight()/4, gc.getWidth(), 50, 1);
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {	
		board.draw();	
		g.setLineWidth(10);
		g.setColor(Color.darkGray);
		g.drawString("InGameState", 0, 0);
		g.draw(counterZone);
		
		paintCardArray(gc);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int time)
			throws SlickException {
		
		Input input  = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			sbg.enterState(1);
		}
		
	}
	
	/**
	 * This method is called every time this state is left.
	 * 
	 * Its current behavior is that it clears the record of 
	 * keys pressed, removing any unwanted behavior in states
	 * switched to.
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
	 * Gets the supply and creates images out of all the cards.
	 * 
	 * <p>In future updates, this should probably also do 
	 * something sensible with the amount of cards left in 
	 * the supply. But we'll get to that when we have to.
	 * 
	 * @return a randomly selected card from the list of cards
	 * @throws SlickException
	 */
	private void getSupply() 
			throws SlickException {
		
		cih = CardInfoHandler.getInstance();
		String[] cardsArray = supply.getCardsInSupply().keySet().toArray(new String[0]);
		cardsToShow = new Image[cardsArray.length];
		
		for(int i = 0; i < cardsArray.length; i++){
			cardsToShow[i] = new Image(cih.getImageLink(cardsArray[i]));
		}	
	}
	
	/**
	 * Returns all the actioncards from an array of cardnames.
	 * 
	 * <p> Although this method promises to return all actioncards, it will also
	 * return a copy of gardens if one is present in the string of cardnames.
	 * This is because a copy of gardens should be displayed with the actioncards
	 * in the supply, and the true purpose of this method is the get the ten cards
	 * that should be displayed in the supply.<p>
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
		
		return cardsToReturn;
	}
	
	/**
	 * Returns all the victorycards from an array of cardnames
	 * 
	 * <p>Although it says victorycards, this method will also fetch curse
	 * cards, as they are somewhat related, at least when they will be drawn 
	 * out in the supply are.<p>
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
		
		return cardsToReturn;
	}
	 /**
	  * Returns all the treasurecards from an array of cardnames
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
		
		return cardsToReturn;
	}
	
	/**
	 * Prints the entire array filled with cards.
	 * 
	 * <p>This method (and the array filled with cards),
	 * is mainly for demonstrative and experimental
	 * purposes and will probably become obsolete or
	 * have to go trough extensive modification.
	 */
	private void paintCardArray(GameContainer gc) {	
		
		int cardWidth = gc.getWidth()/(cardsToShow.length/2);
		for(int i = 0; i < cardsToShow.length; i++){
			double scale = (double)cardWidth/cardsToShow[i].getWidth();
			int cardHeight = (int) (cardsToShow[i].getHeight()*scale);
			if(i < cardsToShow.length/2){
				cardsToShow[i].draw(cardWidth*i, 0, cardWidth, cardHeight);
			} else {
				cardsToShow[i].draw(cardWidth*(i - (cardsToShow.length/2)), cardHeight, cardWidth, cardHeight);
			}
		}
	}

}
