package tda367.dominion.client.view;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import tda367.dominion.client.model.Settings;
import tda367.dominion.commons.listener.GameListener;

public class MainView extends StateBasedGame implements Runnable {

	public static AppGameContainer theGame;

	/**
	 * Constructs a new StateBasedGame which in turn creates a AppGameContainer,
	 * TODO read from file
	 * 
	 * @param title
	 *            of the window
	 */
	public MainView() {
		super("Dominion");
	}

	/**
	 * Initaites all the different states and enters main menu state
	 */
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new SplashScreen(Settings.SPLASHSTATE));
		enterState(Settings.SPLASHSTATE);
		addState(new MainMenuState(Settings.MAINMENUSTATE));
		addState(new InGameState(Settings.INGAMESTATE));
		addState(new ServerListState(Settings.SERVERLISTSTATE));
		addState(new OptionsState(Settings.OPTIONSSTATE));
		addState(new ShowCardState(Settings.SHOWCARDSTATE));
	}

	public void addCardListener(GameListener l) {
		InGameState s = (InGameState) getState(Settings.INGAMESTATE);
		s.addCardListener(l);
	}

	public void addSupplyListener(GameListener l) {
		InGameState s = (InGameState) getState(Settings.INGAMESTATE);
		s.addSupplyListener(l);
	}

	public void addUpdateRoomListener(GameListener l) {
		ServerListState s = (ServerListState) this
				.getState(Settings.SERVERLISTSTATE);
		s.addUpdateRoomListener(l);
	}

	public void addJoinListener(GameListener l) {
		ServerListState s = (ServerListState) this
				.getState(Settings.SERVERLISTSTATE);
		s.addJoinListener(l);
	}

	public void addHostListener(GameListener l) {
		ServerListState s = (ServerListState) this
				.getState(Settings.SERVERLISTSTATE);
		s.addHostListener(l);
	}

	/**
	 * Sets the resolution of the game to width and height
	 * 
	 * @param width
	 * @param height
	 * @throws SlickException
	 */
	public static void setResolution(int width, int height)
			throws SlickException {
		Settings.SCREENHEIGHT = height;
		Settings.SCREENWIDTH = width;
		theGame.setDisplayMode(Settings.SCREENHEIGHT, Settings.SCREENWIDTH,
				Settings.fpsshow);
	}

	/**
	 * Sets the game to chosen fullscreen value (Either false or true) and saves
	 * it to the local fullScreen boolean
	 * 
	 * @param fullScreen
	 * @throws SlickException
	 */
	public static void setFullscreen(boolean fullScreen) throws SlickException {
		Settings.fpsshow = fullScreen;
		theGame.setDisplayMode(Settings.SCREENHEIGHT, Settings.SCREENWIDTH,
				fullScreen);
	}

	public static void showFps(boolean fps) throws SlickException {
		Settings.fpsshow = fps;
		theGame.setShowFPS(Settings.fpsshow);
	}

	public void updateRoomData(String[][] s) {
		GameState g = this.getState(Settings.SERVERLISTSTATE);
		((ServerListState) g).updateRoomData(s);
		System.out.println("Updating game rooms");
	}

	/**
	 * Writes all the current configurations in options to a file and exits the
	 * game
	 */
	public static void exit() {
		PrintWriter writer;
		try {
			writer = new PrintWriter("options.txt", "UTF-8");
			writer.println(Settings.SCREENHEIGHT);
			writer.println(Settings.SCREENWIDTH);
			writer.println(Settings.fullscreen);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		theGame.exit();
	}

	public void updatePlayer(int actions, int buys, int money) {
		InGameState g = ((InGameState) this.getState(Settings.INGAMESTATE));
		g.setActions(actions);
		g.setBuys(buys);
		g.setMoney(money);
	}

	public void updateCards(ArrayList<String> hand, ArrayList<String> inPlay,
			String topOfPile, int deckSize) {
		InGameState g = ((InGameState) this.getState(Settings.INGAMESTATE));
		g.setHand(hand);
		g.setInPlay(inPlay);
		g.setTopOfPile(topOfPile);
		g.setDeckSize(deckSize);
	}

	public void updateSupply(HashMap<String, Integer> supply) {
		InGameState g = ((InGameState) this.getState(Settings.INGAMESTATE));
		if(g.getSupply() == null){
			g.setSupply(supply);
			g.initBoard();
		} else {
			g.setSupply(supply);
		}
	}

	private void startView() {
		try {
			theGame = new AppGameContainer(this);
			theGame.setDisplayMode(Settings.SCREENHEIGHT, Settings.SCREENWIDTH,
					false);
			theGame.setAlwaysRender(true);
			theGame.setVSync(true);
			theGame.setShowFPS(Settings.fpsshow);
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
