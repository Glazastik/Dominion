package tda367.dominion.view;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class MainMenuViewState extends BasicGameState {

	Image exitButton = null;
	Image playButton = null;
	Image options = null;
	Image background = null;
	int id = 0;
	
	public MainMenuViewState(int id) {
		this.id = id;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		exitButton = new Image("res/img/gui/menu/exitGame.png");
		playButton = new Image("res/img/gui/menu/playGame.png");
		options = new Image("res/img/gui/menu/options.png");
		background = new Image("res/img/gui/menu/background.jpg");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawString("Main Menu", 0, 0);
		background.draw();
		exitButton.draw(100, 400);
		playButton.draw(100, 200);
		options.draw(100, 300);
		
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
