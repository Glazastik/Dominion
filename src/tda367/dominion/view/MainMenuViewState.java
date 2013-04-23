package tda367.dominion.view;

import org.lwjgl.input.*;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.*;

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
		exitRec = new Rectangle(100, 50, 200, 50);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		background.draw();
		g.drawString("Main Menu "+mouse , 0, 0);
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
		
		//Checks if mouse cursor is within playgame image
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && playRec.contains(xPos, yPos)) {
			sbg.enterState(0);
		}
		
		//Checks if mouse cursor is within exitgame image
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && exitRec.contains(xPos, yPos)) {
			sbg.getContainer().exit();
		}
		
		//Switches State to gameState
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			sbg.enterState(0);
		}
	}

	@Override
	public int getID() {
		return id;
	}
	
}
