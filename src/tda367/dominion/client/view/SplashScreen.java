package tda367.dominion.view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class SplashScreen extends BasicGameState {

	private Image splash;
	private int elapsedTime;
	private int id;
	private final int DELAY;
	
	public SplashScreen(int id){
		this.id = id;
		DELAY = 3000;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		splash = new Image("res/img/gui/splash/splash.png");
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		splash.draw();
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		elapsedTime += delta;
		
		if(elapsedTime >= DELAY){
			sbg.enterState(1);
		}
		// TODO Auto-generated method stub

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

}
