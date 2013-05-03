package tda367.dominion.client.view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * A class that shows a pretty picture while loading
 * all resources. It is not perfect since Slick2D 
 * loads first, and later enters this state, but it 
 * is as good as it gets.
 * 
 * @author Pontus
 *
 */
public class SplashScreen extends BasicGameState {

	private Image splash;
	private int id;
	
	/**
	 * Creates a new state with the specified ID.
	 * 
	 * @param id the id that this state will be recognized by
	 */
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
	
	/**
	 * Adds all the states to the parent {@link StateBasedGame} and 
	 * initializes them.
	 * 
	 * @param gc the {@link GameContainer} that the game is contained in
	 * @param sbg the {@link StateBasedGame} that controls the game
	 * @throws SlickException
	 */
	private void initStates(GameContainer gc, StateBasedGame sbg) 
			throws SlickException{
		sbg.addState(new MainMenuState(MainView.MAINMENUSTATE, MainView.controller));
		sbg.getState(MainView.MAINMENUSTATE).init(gc, sbg);
		sbg.addState(new InGameState(MainView.INGAMESTATE, MainView.controller));
		sbg.getState(MainView.INGAMESTATE).init(gc, sbg);
		sbg.addState(new ServerListState(MainView.SERVERLISTSTATE, MainView.controller));
		sbg.getState(MainView.SERVERLISTSTATE).init(gc, sbg);
		sbg.addState(new OptionsState(MainView.OPTIONSSTATE, MainView.controller));
		sbg.getState(MainView.OPTIONSSTATE).init(gc, sbg);
		sbg.addState(new ShowCardState(MainView.SHOWCARDSTATE));
		sbg.getState(MainView.SHOWCARDSTATE).init(gc, sbg);
	}

	@Override
	public int getID() {
		return id;
	}

}
