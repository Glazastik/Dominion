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

	public MainMenuViewState(int id) {
		this.id = id;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
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
		background.draw();
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
			sbg.enterState(3, null, createNewHorizontalSplitTransition());
		}

		// Checks if mouse cursor is within exitgame image
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)
				&& exitRec.contains(xPos, yPos)) {
			sbg.getContainer().exit();
		}

		// Checks if mouse cursor is within options image
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)
				&& optionsRec.contains(xPos, yPos)) {
			sbg.enterState(2, null, createNewSelectTransition());
		}

	}
	
	@Override
	public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
		  gc.getInput().clearKeyPressedRecord();
	}

	@Override
	public int getID() {
		return id;
	}

	public Transition createNewHorizontalSplitTransition() {
		Transition splitTransition = null;
		try {
			splitTransition = HorizontalSplitTransition.class.newInstance();
		} catch (Throwable e) {
			Log.error(e);
		}

		return splitTransition;
	}

	public Transition createNewSelectTransition() {
		Transition selectTransition = null;
		try {
			selectTransition = SelectTransition.class.newInstance();
		} catch (Throwable e) {
			Log.error(e);
		}

		return selectTransition;
	}

}
