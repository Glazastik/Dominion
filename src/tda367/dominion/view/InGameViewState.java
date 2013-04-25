package tda367.dominion.view;

import java.util.*;
import tda367.dominion.model.*;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class InGameViewState extends BasicGameState {

	Supply supply;
	CardInfoHandler cih;
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
		cardsToShow = new Image[5];
		amountOfPlayers = 2; //Should probably be supplied from network later
		supply = new Supply(amountOfPlayers);
		
		for(int i = 0; i < cardsToShow.length; i++){
			cardsToShow[i] = getRandomCard();
		}
		
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

	@Override
	public int getID() {
		return id;
	}
	
	/**
	 * Returns a random card of any type
	 * 
	 * @return a randomly selected card from the list of cards
	 * @throws SlickException
	 */
	private Image getRandomCard() 
			throws SlickException {
		cih = CardInfoHandler.getInstance();
		
		List<String> cardList = cih.getCardList();
		Random r = new Random();
		
		int index = r.nextInt(cardList.size()-1);
		return new Image(cih.getImageLink(cardList.get(index)));	
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
		
		int cardWidth = gc.getWidth()/5;
		for(int i = 0; i < cardsToShow.length; i++){
			double scale = (double)cardWidth/cardsToShow[i].getWidth();
			int cardHeight = (int) (cardsToShow[i].getHeight()*scale);
			cardsToShow[i].draw(cardWidth*i, 0, cardWidth, cardHeight);
		}
	}

}
