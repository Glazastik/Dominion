package tda367.dominion.view;

import java.util.*;
import tda367.dominion.model.*;

import org.newdawn.slick.*;
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
	
	public InGameViewState(int id) {
		this.id = id;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		board = new Image("res/img/gui/ingame/BoardTemp.png");
		amountOfPlayers = 2; //Should probably be supplied from network later
		supply = new Supply(amountOfPlayers);
		getSupply();
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {		
		g.drawString("InGameState", 0, 0);
		board.draw();
		
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
		List<String> allActioncards = cih.getActionCards();
		String[] actioncards = new String[10];
		
		int index = 0;
		for(int i = 0; i < cards.length; i++){
			if(allActioncards.contains(cards[i])){
				actioncards[index] = cards[i];
				index++;
			}
		}
		
		for(int i = 0; i < cards.length; i++){
			if(cards[i].equals("Gardens")){//Ensures Gardens DOES end up with the actioncards
				actioncards[index] = cards[i];
			}
		}
		return null;
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
		List<String> allVictorycards = cih.getVictoryCards();
		String[] victorycards = new String[4];
		
		int index = 0;
		for(int i = 0; i < cards.length; i++){
			if(allVictorycards.contains(cards[i])){
				if(!cards[i].equals("Gardens")){//Ensures Gardens does NOT end up with the victorycards
					victorycards[index] = cards[i];
					index++;
				}
			}
		}
		
		for(int i = 0; i < cards.length; i++){
			if(cards[i].equals("Curse")){
				victorycards[index] = cards[i];
			}
		}
		
		return victorycards;
	}
	 /**
	  * Returns all the treasurecards from an array of cardnames
	  * 
	  * @param cards the array of cardnames that will be searched for treasurecards
	  * @return an array containg every treasurecard found
	  */
	private String[] getTreasureCards(String[] cards) {
		return null;
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
