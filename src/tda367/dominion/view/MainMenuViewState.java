package tda367.dominion.view;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class MainMenuViewState extends BasicGameState {

	int id = 0;
	
	public MainMenuViewState(int id) {
		this.id = id;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawString("Main Menu", 0, 0);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int time)
			throws SlickException {
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			sbg.enterState(0);
		}
		
		
	}

	@Override
	public int getID() {
		return id;
	}
	
}
