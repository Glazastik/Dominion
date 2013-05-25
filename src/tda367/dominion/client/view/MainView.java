package tda367.dominion.client.view;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import tda367.dominion.client.model.Settings;
import tda367.dominion.commons.listener.GameListener;

/**
 * This is the first view class to start, it creates and calls all the ViewStates.
 */
public class MainView extends StateBasedGame implements Runnable {

	public static AppGameContainer theGame;
	private String phase;

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
		addState(new MainMenuState(Settings.MAINMENUSTATE));
		addState(new ServerListState(Settings.SERVERLISTSTATE));
		addState(new OptionsState(Settings.OPTIONSSTATE));
		addState(new ShowCardState(Settings.SHOWCARDSTATE));
		addState(new InGameState(Settings.INGAMESTATE));
		addState(new EndGameState(Settings.ENDGAMESTATE));
		enterState(Settings.SPLASHSTATE);
	}

	public void addSettingsListener(GameListener l) {
		OptionsState s = (OptionsState) getState(Settings.OPTIONSSTATE);
		s.addSettingsListener(l);
	}

	public void addExitListener(GameListener l) {
		MainMenuState s = (MainMenuState) getState(Settings.MAINMENUSTATE);
		s.addExitListener(l);
	}

	public void addCardListener(GameListener l) {
		InGameState s = (InGameState) getState(Settings.INGAMESTATE);
		s.addCardListener(l);
	}

	public void addPlayAllListener(GameListener l) {
		InGameState s = (InGameState) getState(Settings.INGAMESTATE);
		s.addPlayAllListener(l);
	}

	public void addSupplyListener(GameListener l) {
		InGameState s = (InGameState) getState(Settings.INGAMESTATE);
		s.addSupplyListener(l);
	}

	public void addAdvanceListener(GameListener l) {
		InGameState s = (InGameState) getState(Settings.INGAMESTATE);
		s.addAdvanceListener(l);
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

	public void addDoneListener(GameListener l) {
		InGameState s = (InGameState) this.getState(Settings.INGAMESTATE);
		s.addDoneListener(l);
	}
	
	public void addBoolListener(GameListener l) {
		InGameState s = (InGameState) this.getState(Settings.INGAMESTATE);
		s.addBoolListener(l);
	}
	
	public void activateYesNoBox(String text) {
		InGameState s = (InGameState) this.getState(Settings.INGAMESTATE);
		s.activateYesNoBox(text);
	}
	
	public void setRevealedCards(String[] cards) {
		InGameState s = (InGameState) this.getState(Settings.INGAMESTATE);
		s.setRevealedCards(cards);
	}
	
	public void setRevealedCard(String cards) {
		InGameState s = (InGameState) this.getState(Settings.INGAMESTATE);
		s.setRevealedCard(cards);
	}
	
	public void setupEndState(LinkedList<String> names, LinkedList<Integer> scores){
		EndGameState egs = (EndGameState) this.getState(Settings.ENDGAMESTATE);
		egs.initData(names, scores);
		this.enterState(Settings.ENDGAMESTATE);
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
			writer.println(Settings.fpsshow);
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

	public void updateCards(LinkedList<String> hand, LinkedList<String> inPlay,
			String topOfPile, int deckSize) {
		InGameState g = ((InGameState) this.getState(Settings.INGAMESTATE));
		g.setHand(hand);
		g.setInPlay(inPlay);
		g.setTopOfPile(topOfPile);
		g.setDeckSize(deckSize);
	}

	public void updateSupply(HashMap<String, Integer> supply) {
		InGameState g = ((InGameState) this.getState(Settings.INGAMESTATE));
		g.setSupply(supply);
	}

	public void updatePlayersInfo(String[] names) {
		InGameState g = ((InGameState) this.getState(Settings.INGAMESTATE));
		g.setPlayerNames(names);
	}

	public void updatePhase(String phase) {
		InGameState g = ((InGameState) this.getState(Settings.INGAMESTATE));
		g.setPhase(phase);
	}

	/**
	 * A method for starting and initiating the view, also reads settings from
	 * an options file.
	 */
	private void startView() {
		try {
			// Reads the file containing all the info about the options
			FileInputStream fstream = new FileInputStream("options.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			int line = 0;
			String height = null;
			String width = null;
			while ((strLine = br.readLine()) != null) {
				if (line == 0) {
					width = strLine;
				} else if (line == 1) {
					height = strLine;
				} else if (line == 2) {
					if (strLine.equals("true")) {
						Settings.fullscreen = true;
					} else {
						Settings.fullscreen = false;
					}
				} else if (line == 3) {
					if (strLine.equals("true")) {
						Settings.fpsshow = true;
					} else {
						Settings.fpsshow = false;
					}
				}
				line++;
			}
			if (height != null && width != null) {
				Settings.setResolution(Integer.parseInt(width),
						Integer.parseInt(height));
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			theGame = new AppGameContainer(this);
			theGame.setDisplayMode(Settings.SCREENHEIGHT, Settings.SCREENWIDTH,
					Settings.fullscreen);
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

	/**
	 * Updates the resolution, fullscreen and fps settings. Also writes them to
	 * a file.
	 */
	public static void updateSettings() {
		try {
			theGame.setDisplayMode(Settings.SCREENHEIGHT, Settings.SCREENWIDTH,
					Settings.fullscreen);
			theGame.setShowFPS(Settings.fpsshow);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		PrintWriter writer;
		try {
			writer = new PrintWriter("options.txt", "UTF-8");
			writer.println(Settings.SCREENHEIGHT);
			writer.println(Settings.SCREENWIDTH);
			writer.println(Settings.fullscreen);
			writer.println(Settings.fpsshow);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public void updateTip(String message) {
		InGameState g = ((InGameState) this.getState(Settings.INGAMESTATE));
		g.setTipMessage(message);
	}
	
	public void updateLog(String message) {
		InGameState g = ((InGameState) this.getState(Settings.INGAMESTATE));
		g.addLogMessage(message);
	}

}
