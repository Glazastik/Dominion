package tda367.dominion.view;

import java.util.*;
import tda367.dominion.model.*;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class InGameViewState extends BasicGameState {

	CardInfoHandler cih;
	Image[] cardsToShow;
	Image board = null;
	int id = 0;
	
	public InGameViewState(int id) {
		this.id = id;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		board = new Image("res/img/gui/ingame/BoardTemp.png");
		cardsToShow = new Image[5];
		
		cih = CardInfoHandler.getInstance();
		
		List<String> cardList = cih.getActionCards();
		Random r = new Random();
		for(int i = 0; i < cardsToShow.length; i++){
			cardsToShow[i] = new Image(cih.getImageLink(cardList.get(r.nextInt(cardList.size()-1))));//Who says nestled code is bad?
		}
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		int cardWidth;
		double scale;
		int cardHeight;
		
		g.drawString("InGameState", 0, 0);
		board.draw();
		
		cardWidth = gc.getWidth()/5;
		for(int i = 0; i < cardsToShow.length; i++){
			scale = (double)cardWidth/cardsToShow[i].getWidth();
			cardHeight = (int) (cardsToShow[i].getHeight()*scale);
			cardsToShow[i].draw(cardWidth*i, 0, cardWidth, cardHeight);
		}
		
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

}
