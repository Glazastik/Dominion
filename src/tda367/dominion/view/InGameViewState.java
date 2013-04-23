package tda367.dominion.view;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class InGameViewState extends BasicGameState {

	Image hrtatk = null;
	int id = 0;
	
	public InGameViewState(int id) {
		this.id = id;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		hrtatk = new Image("res/img/card/bonus/Heartattack.png");
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		hrtatk.draw();
		g.drawString("InGameState", 0, 0);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int time)
			throws SlickException {
		
		Input input  = gc.getInput();
		
		if (input.isKeyDown(Input.KEY_SPACE)) {
			sbg.enterState(1);
		}
		
	}

	@Override
	public int getID() {
		return id;
	}

}
