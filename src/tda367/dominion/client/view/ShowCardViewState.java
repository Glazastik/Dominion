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
	private String showCard;
	private Image bigCard;
	private Image backdrop;
	
	public ShowCardViewState(int id) {
		ID = id;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		backdrop = new Image("res/img/gui/ingame/BoardTemp.png");
		bigCard = new Image(CardInfoHandler.getInstance().getImageLink(showCard));
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		if(bigCard != null) {
			bigCard.draw(200, 200);
		}
		backdrop.draw();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		if(input.equals(Input.MOUSE_RIGHT_BUTTON)) {
			sbg.enterState(0);
		}
	}

	@Override
	public int getID() {
		return ID;
	}
}
