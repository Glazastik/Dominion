package tda367.dominion.client.view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * A state for taking a closer look at a card.
 * 
 * @author Group 28
 */
public class ShowCardState extends BasicGameState {

	private final int ID;
	private Image bigCard;
	private Image backdrop;
	private boolean leave = false;
	
	public ShowCardState(int id) {
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
		if(leave == true) {
			leave = false;
			sbg.enterState(0);
		}
	}
	
	/**
	 * Takes care of all actions that calls mouseClicked
	 */
	@Override
	public void mouseClicked(int button, int x, int y, int clicks) {
		super.mouseClicked(button, x, y, clicks);
		if(button == Input.MOUSE_RIGHT_BUTTON) {
			leave = true;
		} else if(button == Input.MOUSE_LEFT_BUTTON) {
			leave = true;
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
