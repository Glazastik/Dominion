package tda367.dominion.view;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class MainView extends StateBasedGame {
	
	public final int INGAMESTATE = 0;
	public final int MAINMENUSTATE = 1;
	public final int OPTIONSSTATE = 2;
	public final int SERVERLISTSTATE = 3;
	public static int screenHeight = 800;
	public static int screenWidth = 600;
	public static AppGameContainer theGame;
	public static boolean fullS = false;
	
	
	/**
	 * Constructs a new StateBasedGame which in turn creates a AppGameContainer, TODO read from file
	 * @param title of the window
	 */
	public MainView(String title) {
		super(title);
		
		try {
			theGame = new AppGameContainer(this);
			theGame.setDisplayMode(screenHeight, screenWidth, false);
	        theGame.setAlwaysRender(true);
	        theGame.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initaites all the different states and enters main menu state
	 */
	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		addState(new InGameViewState(INGAMESTATE));
		addState(new MainMenuViewState(MAINMENUSTATE));
		addState(new OptionsViewState(OPTIONSSTATE));
		addState(new ServerListState(SERVERLISTSTATE));
		enterState(MAINMENUSTATE);
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
    	theGame.setDisplayMode(1280, 800, fullScreen);
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
}
