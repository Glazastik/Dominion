package tda367.dominion.view;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class GameViewState extends BasicGameState {

	Image hrtatk = null;
	private int id = 0;
	
	public GameViewState(int id) {
		this.id = id;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		hrtatk = new Image("res/img/card/bonus/Heartattack.png");
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		hrtatk.draw(200, 200);
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		
		
	}

	@Override
	public int getID() {
		return id;
	}

}
