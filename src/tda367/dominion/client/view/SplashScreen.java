package tda367.dominion.client.view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class SplashScreen extends BasicGameState {

	private Image splash;
	private int id;
	
	public SplashScreen(int id){
		this.id = id;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		splash = new Image("res/img/gui/splash/splash.png");
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		splash.draw();

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {		
			initStates(gc, sbg);
			sbg.enterState(1);
			
	}
	
	private void initStates(GameContainer gc, StateBasedGame sbg) 
			throws SlickException{
		sbg.addState(new MainMenuViewState(MainView.MAINMENUSTATE, MainView.controller));
		sbg.getState(MainView.MAINMENUSTATE).init(gc, sbg);
		sbg.addState(new InGameViewState(MainView.INGAMESTATE, MainView.controller));
		sbg.getState(MainView.INGAMESTATE).init(gc, sbg);
		sbg.addState(new ServerListState(MainView.SERVERLISTSTATE, MainView.controller));
		sbg.getState(MainView.SERVERLISTSTATE).init(gc, sbg);
		sbg.addState(new OptionsViewState(MainView.OPTIONSSTATE, MainView.controller));
		sbg.getState(MainView.OPTIONSSTATE).init(gc, sbg);
	}

	@Override
	public int getID() {
		return id;
	}

}
