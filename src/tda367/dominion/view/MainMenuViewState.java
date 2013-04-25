package tda367.dominion.view;

import org.lwjgl.input.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;
import org.newdawn.slick.util.Log;
import org.lwjgl.util.Rectangle;

public class MainMenuViewState extends BasicGameState {

	String mouse = "";
	Image exitButton = null;
	Image playButton = null;
	Image options = null;
	Image background = null;

	Rectangle playRec = null;
	Rectangle exitRec = null;
	Rectangle optionsRec = null;

	int id = 0;

	/**
	 * Creates a new instance of this state with the supplied ID.
	 * 
	 * @param id the ID this state will be identified with
	 */
	public MainMenuViewState(int id) {
		this.id = id;
	}

	/**
	 * This method is called when this state is initialized.
	 * 
	 * @param gc the {@link GameContainer} this state is contained in
	 * @param sbg the {@link StateBasedGame} this state is a part of
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		exitButton = new Image("res/img/gui/menu/exitGame.png");
		playButton = new Image("res/img/gui/menu/playGame.png");
		options = new Image("res/img/gui/menu/options.png");
		background = new Image("res/img/gui/menu/background.jpg");

		playRec = new Rectangle(100, 350, 200, 50);
		exitRec = new Rectangle(100, 150, 200, 50);
		optionsRec = new Rectangle(100, 250, 200, 50);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		background.draw(0 , 0);
		g.drawString("Main Menu " + mouse, 0, 0);
		exitButton.draw(100, 400);
		playButton.draw(100, 200);
		options.draw(100, 300);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int time)
			throws SlickException {
		Input input = gc.getInput();
		int xPos = Mouse.getX();
		int yPos = Mouse.getY();
		mouse = "X: " + xPos + " Y: " + yPos;

		// Checks if mouse cursor is within playgame image
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)
				&& playRec.contains(xPos, yPos)) {
			sbg.enterState(3, null, Transitions.createNewSelectTransition());
		} else if(input.isKeyPressed(Input.KEY_1)) {
			sbg.enterState(3, null, Transitions.createNewSelectTransition());
		}

		// Checks if mouse cursor is within exitgame image
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)
				&& exitRec.contains(xPos, yPos)) {
			sbg.getContainer().exit();
		} else if(input.isKeyPressed(Input.KEY_3)) {
			sbg.getContainer().exit();
		}

		// Checks if mouse cursor is within options image
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)
				&& optionsRec.contains(xPos, yPos)) {
			sbg.enterState(2, null, Transitions.createNewSelectTransition());
		}  else if(input.isKeyPressed(Input.KEY_2)) {
			sbg.enterState(2, null, Transitions.createNewSelectTransition());
		}

	}
	
	/**
	 * This method is called every time this state is left.
	 * 
	 * Its current behavior is that it clears the record of 
	 * keys pressed, removing any unwanted behavior in states
	 * switched to.
	 * 
	 * @param gc the {@link GameContainer} this state is contained in
	 * @param sbg the {@link StateBasedGame} this state is a part of
	 */
	@Override
	public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
		  gc.getInput().clearKeyPressedRecord();
	}

	/**
	 * Returns the ID of this state.
	 * 
	 * @return an int that is representative of this state
	 */
	@Override
	public int getID() {
		return id;
	}

}
