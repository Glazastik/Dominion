package tda367.dominion.view;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import tda367.dominion.controller.ClientController;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class MainView extends StateBasedGame {
	
	public final int INGAMESTATE = 0;
	public final int MAINMENUSTATE = 1;
	public final int OPTIONSSTATE = 2;
	public final int SERVERLISTSTATE = 3;
	public static int screenHeight = 1280;
	public static int screenWidth = 800;
	public static AppGameContainer theGame;
	public static boolean fullS = false;
	public static ClientController controller;
	
	
	/**
	 * Constructs a new StateBasedGame which in turn creates a AppGameContainer, TODO read from file
	 * @param title of the window
	 */
	public MainView(ClientController controller) {
		super("Dominion");
		this.controller = controller;
	}

	/**
	 * Initaites all the different states and enters main menu state
	 */
	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		addState(new InGameViewState(INGAMESTATE, controller));
		addState(new MainMenuViewState(MAINMENUSTATE, controller));
		addState(new OptionsViewState(OPTIONSSTATE, controller));
		addState(new ServerListState(SERVERLISTSTATE, controller));
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
    	theGame.setDisplayMode(screenHeight, screenWidth, fullScreen);
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

	public void start() {
		try {
			theGame = new AppGameContainer(this);
			theGame.setDisplayMode(screenHeight, screenWidth, false);
	        theGame.setAlwaysRender(true);
	        theGame.setVSync(true);
	        theGame.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
