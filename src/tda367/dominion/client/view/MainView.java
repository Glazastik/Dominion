package tda367.dominion.client.view;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import tda367.dominion.client.model.Global;
import tda367.dominion.commons.listener.GameListener;

public class MainView extends StateBasedGame implements Runnable {
	
	
	public static AppGameContainer theGame;
	
	/**
	 * Constructs a new StateBasedGame which in turn creates a AppGameContainer, TODO read from file
	 * @param title of the window
	 */
	public MainView() {
		super("Dominion");
	}

	/**
	 * Initaites all the different states and enters main menu state
	 */
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new SplashScreen(Global.SPLASHSTATE));
		enterState(Global.SPLASHSTATE);
		addState(new MainMenuState(Global.MAINMENUSTATE));
		getState(Global.MAINMENUSTATE).init(gc, this);
		addState(new InGameState(Global.INGAMESTATE));
		getState(Global.INGAMESTATE).init(gc, this);
		addState(new ServerListState(Global.SERVERLISTSTATE));
		getState(Global.SERVERLISTSTATE).init(gc, this);
		addState(new OptionsState(Global.OPTIONSSTATE));
		getState(Global.OPTIONSSTATE).init(gc, this);
		addState(new ShowCardState(Global.SHOWCARDSTATE));
		getState(Global.SHOWCARDSTATE).init(gc, this);
	}
	
	public void addCardListener(GameListener l) {
		InGameState s = (InGameState)getState(Global.INGAMESTATE);
		s.addCardListener(l);
	}
	
	public void addSupplyListener(GameListener l) {
		InGameState s = (InGameState)getState(Global.INGAMESTATE);
		s.addSupplyListener(l);
	}
    
    /**
     * Sets the resolution of the game to width and height
     * @param width
     * @param height
     * @throws SlickException
     */
	public static void setResolution(int width, int height) throws SlickException {
    	Global.SCREENHEIGHT = height;
    	Global.SCREENWIDTH = width;
    	theGame.setDisplayMode(Global.SCREENHEIGHT, Global.SCREENWIDTH, Global.FPSSHOW);
    }
    
	/**
	 * Sets the game to chosen fullscreen value (Either false or true) and saves it to the local fullScreen boolean
	 * @param fullScreen
	 * @throws SlickException
	 */
    public static void setFullscreen(boolean fullScreen) throws SlickException {
    	Global.FPSSHOW = fullScreen;
    	theGame.setDisplayMode(Global.SCREENHEIGHT, Global.SCREENWIDTH, fullScreen);
    }
    
    public static void showFps(boolean fps) throws SlickException {
    	Global.FPSSHOW = fps;
    	theGame.setShowFPS(Global.FPSSHOW);
    }
    
    public void updateRoomData(String[][] s) {
    	GameState g = this.getState(Global.SERVERLISTSTATE);
    	((ServerListState) g).updateRoomData(s);
    	System.out.println("Updating game rooms");
    }
    
    /**
     * Writes all the current configurations in options to a file and exits the game
     */
    public static void exit() {
    	PrintWriter writer;
		try {
			writer = new PrintWriter("options.txt", "UTF-8");
	    	writer.println(Global.SCREENHEIGHT);
	    	writer.println(Global.SCREENWIDTH);
	    	writer.println(Global.FULLSCREEN);
	    	writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		theGame.exit();
    }
    
    public void updatePlayer(int actions, int buys, int money) {
    	InGameState g = ((InGameState)this.getState(Global.INGAMESTATE));
    	g.setActions(actions);
    	g.setBuys(buys);
    	g.setMoney(money);
    }
    
    public void updateCards(ArrayList<String> hand, ArrayList<String> inPlay, String topOfPile, int deckSize) {
    	InGameState g = ((InGameState)this.getState(Global.INGAMESTATE));
    	g.setHand(hand);
    	g.setInPlay(inPlay);
    	g.setTopOfPile(topOfPile);
    	g.setDeckSize(deckSize);
    }

	private void startView() {
		try {
			theGame = new AppGameContainer(this);
			theGame.setDisplayMode(Global.SCREENHEIGHT, Global.SCREENWIDTH, false);
	        theGame.setAlwaysRender(true);
	        theGame.setVSync(true);
	        theGame.setShowFPS(Global.FPSSHOW);
	        theGame.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		startView();
		
	}
}
