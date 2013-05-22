package tda367.dominion.client.view;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import tda367.dominion.client.model.Settings;
import tda367.dominion.commons.listener.GameEvent;
import tda367.dominion.commons.listener.GameListener;

public class MainMenuState extends ControlledGameState {

	String mouse = "";
	Image exitButton = null;
	int xOffset;
	int yOffset;
	int gap;
	int exitY;
	int playY;
	int optionsY;
	Image playButton = null;
	Image options = null;
	Image background = null;
	Image logo = null;
	Image hover = null;
	Image audio = null;
	Image muted = null;

	Rectangle playRec = null;
	Rectangle exitRec = null;
	Rectangle optionsRec = null;
	Rectangle audioRec = null;

	Music openingMenuMusic;
	float position;
	int volume;
	int pitch;
	
	private GameListener exitGame;
	
	public void addExitListener(GameListener l) {
		exitGame = l;
	}
	
	public void exitGame() {
		GameEvent e = new GameEvent();
		exitGame.run(e);
	}

	/**
	 * Creates a new instance of this state with the supplied ID and controller.
	 * 
	 * @param id
	 *            the ID this state will be identified with
	 */
	public MainMenuState(int id) {
		super(id);
	}

	/**
	 * This method is called when this state is initialized.
	 * 
	 * @param gc
	 *            the {@link GameContainer} this state is contained in
	 * @param sbg
	 *            the {@link StateBasedGame} this state is a part of
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		gap = 40;

		exitButton = new Image("res/img/gui/menu/newexitgame.png");
		playButton = new Image("res/img/gui/menu/newplaygame.png");
		options = new Image("res/img/gui/menu/newoptionsmenu.png");
		background = new Image("res/img/gui/menu/background.jpg");
		hover = new Image("res/img/gui/menu/hover.png");
		logo = new Image("res/img/gui/menu/logo.png");
		audio = new Image("res/img/gui/menu/audio.png");
		muted = new Image("res/img/gui/menu/muted.png");

		setOffsets(gc);

		playRec = new Rectangle(0, 0, playButton.getWidth(),
				playButton.getHeight());
		exitRec = new Rectangle(0, 0, exitButton.getWidth(),
				exitButton.getHeight());
		optionsRec = new Rectangle(0, 0, options.getWidth(),
				options.getHeight());
		audioRec = new Rectangle();

		setRecs();

		gc.setMouseCursor("res/img/gui/menu/wow3.png", 0, 0);

		volume = 100;
		openingMenuMusic = new Music("res/sfx/music3.wav");
		// openingMenuMusic.loop();

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		background.draw();
		g.drawString("Main Menu " + mouse, 0, 0);

		drawMenuItems();
		drawAudioIcons();

		logo.draw(20, 20);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int time)
			throws SlickException {
		Input input = gc.getInput();
		int xPos = Mouse.getX();
		int yPos = gc.getHeight() - Mouse.getY();
		mouse = "X: " + xPos + " Y: " + yPos;

		setOffsets(gc);
		setRecs();

		// Checks if mouse cursor is within playgame image
		if ((input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && playRec
				.contains(xPos, yPos)) || input.isKeyPressed(Input.KEY_1)) {
			if (Settings.inGame) {
				sbg.enterState(Settings.INGAMESTATE, null,
						Transitions.createNewHorizontalSplitTransition());
			} else {
				sbg.enterState(Settings.SERVERLISTSTATE, null,
						Transitions.createNewHorizontalSplitTransition());
			}
		}

		// Checks if mouse cursor is within exitgame image
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)
				&& exitRec.contains(xPos, yPos)) {
			exitGame();
		} else if (input.isKeyPressed(Input.KEY_3)) {
			exitGame();
		}

		// Checks if mouse cursor is within options image
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)
				&& optionsRec.contains(xPos, yPos)) {
			sbg.enterState(2, null,
					Transitions.createNewHorizontalSplitTransition());
		} else if (input.isKeyPressed(Input.KEY_2)) {
			sbg.enterState(2, null,
					Transitions.createNewHorizontalSplitTransition());
		}

		if (input.isKeyPressed(Input.KEY_0)) {
			sbg.enterState(0, null,
					Transitions.createNewHorizontalSplitTransition());
		}

		openingMenuMusic.setVolume(volume);

	}
	
	/**
	 * Takes care of all actions that calls mouseClicked
	 */
	@Override
	public void mouseClicked(int button, int x, int y, int clicks) {
		
		// Checks if the mouse cursor is within the audio image
		if (button == Input.MOUSE_LEFT_BUTTON && audioRec.contains(x, y)) {
			if (openingMenuMusic.playing()) {
				openingMenuMusic.pause();
			} else {
				openingMenuMusic.loop((float)1.5, volume);
			}
		}
	}

	/**
	 * Handles all keyPressed actions
	 */
	@Override
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_M) {
			if (openingMenuMusic.playing()) {
				openingMenuMusic.pause();
			} else {
				openingMenuMusic.play((float) 1.5, 100);
			}
		} else if (key == Input.KEY_DOWN) {
			volume -= 10;
		} else if (key == Input.KEY_UP) {
			volume += 10;
		}
	}

	/**
	 * This method is called every time this state is left.
	 * 
	 * Its current behavior is that it clears the record of keys pressed,
	 * removing any unwanted behavior in states switched to.
	 * 
	 * @param gc
	 *            the {@link GameContainer} this state is contained in
	 * @param sbg
	 *            the {@link StateBasedGame} this state is a part of
	 */
	@Override
	public void leave(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		gc.getInput().clearKeyPressedRecord();
	}

	private void setOffsets(GameContainer gc) {
		xOffset = (gc.getWidth() - (exitButton.getWidth()
				+ playButton.getWidth() + options.getWidth() + 2 * gap)) / 2;
		yOffset = (gc.getHeight() - playButton.getHeight()) / 2;
	}

	private void drawMenuItems() {
		if (playRec.contains(Mouse.getX(), Mouse.getY())) {
			hover.draw(xOffset - 4, yOffset - 3);
		} else if (optionsRec.contains(Mouse.getX(), Mouse.getY())) {
			hover.draw(xOffset + playButton.getWidth() + gap - 4, yOffset - 3);
		} else if (exitRec.contains(Mouse.getX(), Mouse.getY())) {
			hover.draw(
					xOffset + playButton.getWidth() + gap + options.getWidth()
							+ gap - 4, yOffset - 3);
		}
		playButton.draw(xOffset, yOffset);
		options.draw(xOffset + playButton.getWidth() + gap, yOffset);
		exitButton.draw(
				xOffset + playButton.getWidth() + gap + options.getWidth()
						+ gap, yOffset);

	}

	/**
	 * A method for drawing the audio icon in the bottom right corner.
	 */
	private void drawAudioIcons() {
		if (openingMenuMusic.playing()) {
			audio.draw(1210, 710);
			audioRec.setBounds(1210, 710, 64, 64);
		} else {
			muted.draw(1210, 710);
		}
	}

	private void setRecs() {
		playRec.setLocation(xOffset, yOffset);
		optionsRec.setLocation(xOffset + playButton.getWidth() + gap, yOffset);
		exitRec.setLocation(
				xOffset + playButton.getWidth() + gap + options.getWidth()
						+ gap, yOffset);
	}

}
