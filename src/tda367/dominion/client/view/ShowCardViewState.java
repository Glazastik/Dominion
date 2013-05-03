package tda367.dominion.client.view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import tda367.dominion.server.model.CardInfoHandler;

public class ShowCardViewState extends BasicGameState {

	private final int ID;
	private Image bigCard;
	private Image backdrop;
	
	public ShowCardViewState(int id) {
		ID = id;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		backdrop = new Image("res/img/gui/ingame/BoardTemp.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		backdrop.draw();
		if(bigCard != null) {
			bigCard.draw(640 - bigCard.getWidth()/2, 400 - bigCard.getHeight()/2);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		if(input.isKeyPressed(Input.KEY_SPACE)) {
			sbg.enterState(0);
		} else if(input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
			sbg.enterState(0);
		} else if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sbg.enterState(0);
		}
	}
	
	public void showCard(Image card) 
			throws SlickException {
		bigCard = card;
	}

	@Override
	public int getID() {
		return ID;
	}
}
