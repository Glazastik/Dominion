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

import tda367.dominion.client.listener.ViewListener;

public class MainView extends StateBasedGame {
	
	public static final int INGAMESTATE = 0;
	public static final int MAINMENUSTATE = 1;
	public static final int OPTIONSSTATE = 2;
	public static final int SERVERLISTSTATE = 3;
	public static final int SPLASHSTATE = 4;
	public static final int SHOWCARDSTATE = 5;
	public static int screenHeight = 1280;
	public static int screenWidth = 800;
	public static AppGameContainer theGame;
	public static boolean fullS = false;
	public static boolean fpsSet = false;
	private ArrayList<ViewListener> listeners = new ArrayList<ViewListener>();
	
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
	public void initStatesList(GameContainer arg0) throws SlickException {
		addState(new SplashScreen(SPLASHSTATE));
		enterState(SPLASHSTATE);
	}
	
	public void addCardListener(ViewListener l) {
		listeners.add(l);
	}
    
    /**
     * Sets the resolution of the game to width and height
     * @param width
     * @param height
     * @throws SlickException
     */
	public static void setResolution(int width, int height) throws SlickException {
    	screenHeight = height;
    	screenWidth = width;
    	theGame.setDisplayMode(screenHeight, screenWidth, fullS);
    }
    
	/**
	 * Sets the game to chosen fullscreen value (Either false or true) and saves it to the local fullScreen boolean
	 * @param fullScreen
	 * @throws SlickException
	 */
    public static void setFullscreen(boolean fullScreen) throws SlickException {
    	fullS = fullScreen;
    	theGame.setDisplayMode(screenHeight, screenWidth, fullScreen);
    }
    
    public static void showFps(boolean fps) throws SlickException {
    	fpsSet = fps;
    	theGame.setShowFPS(fpsSet);
    }
    
    public void updateRoomData(String[][] s) {
    	GameState g = this.getState(SERVERLISTSTATE);
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
	    	writer.println(screenHeight);
	    	writer.println(screenWidth);
	    	writer.println(fullS);
	    	writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		theGame.exit();
    }
    
    public void updatePlayer(int actions, int buys, int money) {
    	InGameState g = ((InGameState)this.getState(INGAMESTATE));
    	g.setActions(actions);
    	g.setBuys(buys);
    	g.setMoney(money);
    }
    
    public void updateCards(ArrayList hand, ArrayList inPlay, String topOfPile, int deckSize) {
    	InGameState g = ((InGameState)this.getState(INGAMESTATE));
    	g.setHand(hand);
    	g.setInPlay(inPlay);
    	g.setTopOfPile(topOfPile);
    	g.setDeckSize(deckSize);
    }

	public void start() {
		try {
			theGame = new AppGameContainer(this);
			theGame.setDisplayMode(screenHeight, screenWidth, false);
	        theGame.setAlwaysRender(true);
	        theGame.setVSync(true);
	        theGame.setShowFPS(fpsSet);
	        theGame.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
