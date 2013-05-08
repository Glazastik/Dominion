package tda367.dominion.client.view;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import tda367.dominion.client.controller.ClientController;

public class MainMenuState extends ControlledGameState {

	String mouse = "";
	Image exitButton = null;
	int exitY = 400;
	int playY = 200;
	int optionsY = 300;
	Image playButton = null;
	Image options = null;
	Image background = null;
	Image logo = null;

	Rectangle playRec = null;
	Rectangle exitRec = null;
	Rectangle optionsRec = null;

	/**
	 * Creates a new instance of this state with the supplied ID and controller.
	 * 
	 * @param id the ID this state will be identified with
	 */
	public MainMenuState(int id) {
		super(id);
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
		logo = new Image("res/img/gui/menu/logo.png");
		
		playY = (gc.getHeight()/4);
		optionsY = playY + gc.getHeight()/5;
		exitY = optionsY + gc.getHeight()/5;

		playRec = new Rectangle(100, playY - 50, 200, 50);
		exitRec = new Rectangle(100, exitY - 50, 200, 50);
		optionsRec = new Rectangle(100, optionsY - 50, 200, 50);
		
		gc.setMouseCursor("res/img/gui/menu/wow2.gif", 0, 0);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		background.draw();
		g.drawString("Main Menu " + mouse, 0, 0);	
		
		exitButton.draw(100, exitY);
		playButton.draw(100, playY);
		options.draw(100, optionsY);
		logo.draw(20 ,20);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int time)
			throws SlickException {
		Input input = gc.getInput();
		int xPos = Mouse.getX();
		int yPos = gc.getHeight()-Mouse.getY();
		mouse = "X: " + xPos + " Y: " + yPos;
		
		//Update yPos location of buttons and rectangles
		playY = (gc.getHeight()/4);
		optionsY = playY + gc.getHeight()/5;
		exitY = optionsY + gc.getHeight()/5;
		
		playRec.setY(playY);
		optionsRec.setY(optionsY);
		exitRec.setY(exitY);

		// Checks if mouse cursor is within playgame image
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)
				&& playRec.contains(xPos, yPos)) {
			sbg.enterState(3, null, Transitions.createNewHorizontalSplitTransition());
		} else if(input.isKeyPressed(Input.KEY_1)) {
			sbg.enterState(3, null, Transitions.createNewHorizontalSplitTransition());
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
			sbg.enterState(2, null, Transitions.createNewHorizontalSplitTransition());
		}  else if(input.isKeyPressed(Input.KEY_2)) {
			sbg.enterState(2, null, Transitions.createNewHorizontalSplitTransition());
		}
		
		if(input.isKeyPressed(Input.KEY_0)){
			sbg.enterState(0, null, Transitions.createNewHorizontalSplitTransition());
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

}
